<%-- 
    Document   : ListarClientes
    Created on : 01/11/2019, 20:01:49
    Author     : marcelo.smoraes2
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>Lista de Clientes</h2>
<section class="p-5">
    <table class="table">
        <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Nome</th>
                <th scope="col">Data de Nascimento</th>
                
            </tr>
        </thead>
        <tbody>
            <c:forEach var="client" items="${clientList}">
                <tr>
                    <th scope="row"><c:out value="${client.id}" /></th>
                    <td><c:out value="${client.nomeCompleto}"/></td>
                    <td><c:out value="${client.dataNascimentoString}"/></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</section>
<div class="d-flex justify-content-center">
    <a href="/prova1a/client/new" class="btn btn-success"   >Novo cliente</a>
</div>
