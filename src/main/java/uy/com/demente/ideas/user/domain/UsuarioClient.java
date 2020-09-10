package uy.com.demente.ideas.user.domain;

import uy.com.demente.ideas.transversal.handler.exception.BadRequestException;
import uy.com.demente.ideas.transversal.handler.exception.InternalServerErrorException;
import uy.com.demente.ideas.user.domain.model.UserList;
import uy.com.demente.ideas.user.domain.model.Usuario;

public interface UsuarioClient {

	Usuario signIn(Usuario usuario) throws BadRequestException, InternalServerErrorException;

	UserList getUsers() throws BadRequestException, InternalServerErrorException;

	Usuario findUserById(Long idUser) throws BadRequestException, InternalServerErrorException;

	Usuario update(Long idUser, Usuario usuario) throws BadRequestException, InternalServerErrorException;
}
