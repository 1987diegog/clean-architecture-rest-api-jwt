package uy.com.demente.ideas.transversal.audit;

import uy.com.demente.ideas.shared.constanst.AuditPackageConstants;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class AuditLogController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * This Pointcut is transversal to all Controllers (Web REST endpoints).
     */
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void annotationPointcut() {
        // This method has no implementation since it is only a Pointcut dictated by ANNOTATION,
        // the implementation is in the Advices.
    }

    /**
     * Este Pointcut that matches all Spring beans in the application's main packages.
     */
    @Pointcut("within(" + AuditPackageConstants.USER_CONTROLLER_PACKAGE + ") || " +
            "within("+ AuditPackageConstants.ACCOUNT_CONTROLLER_PACKAGE + ")")
    public void packagePointcut() {
        // This method has no implementation since it is only a Pointcut dictated by PACKAGE,
        // the implementation is in the Advices.
    }

    @AfterThrowing(pointcut = "packagePointcut() && annotationPointcut()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        log.error("[EXCEPTION_IN - {}], CAUSE = {}", joinPoint.getSignature().toShortString(),
                exception.getCause() != null ? exception.getCause() : "NULL");
    }

    /**
     * Advice that logs the input and output of the methods with pointcut.
     */
    @Around("packagePointcut() && annotationPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        try {

            // ------> Before executing the method
            long startTime = System.currentTimeMillis();
            log.info("[START - {}], WITH_ARGS = {}", joinPoint.getSignature().toShortString(),
                    Arrays.toString(joinPoint.getArgs()));

            // ------> Execution of the method
            Object result = joinPoint.proceed();

            // ------> After executing the method
            if(result != null) {

                if (result instanceof List) {
                    if (result != null) {
                        log.info("[END - {}], RESULT_SIZE_LIST = [{}]", joinPoint.getSignature().toShortString(),
                                ((List) result).size());
                    } else {
                        log.info("[END - {}], RESULT_LIST IS NULL", joinPoint.getSignature().toShortString());
                    }
                }

                log.info("[END - {}], RESULT = [{}] ", joinPoint.getSignature().toShortString(),
                        result.toString());
            } else {
                log.info("[END - {}], RESULT IS NULL ", joinPoint.getSignature().toShortString());
            }

            // ------> Return to flow
            log.info("[TIEMPO_TOTAL - {}] -> {} milliseconds ", joinPoint.getSignature().toShortString(), System.currentTimeMillis() - startTime);

            return result;

        } catch (Exception e) {
            log.error("[EXCEPTION - {}], WITH_ARGS = {}", joinPoint.getSignature().toShortString(),
                    Arrays.toString(joinPoint.getArgs()));
            throw e;
        }
    }
}

