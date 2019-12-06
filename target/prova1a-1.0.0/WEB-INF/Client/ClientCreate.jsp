<%-- 
    Document   : FormCliente
    Created on : 01/11/2019, 20:01:01
    Author     : marcelo.smoraes2
--%>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

 <link href="${pageContext.request.contextPath}/css/client.css" rel="stylesheet"/>
<div class="wrapper fadeInDown cbg-image">
    <div id="formContent" class="p-3">
        <form  method="post" action="${pageContext.request.contextPath}/client">
            <div class="form-group d-flex flex-column justify-content-center align-items-center">
                <label>Nome</label>
                <input type="text" name="name" class="form-control" value="${nomeCompleto}" placeholder="Digite o nome do cliente..." required/>
            </div>
            <div class="form-group d-flex flex-column justify-content-center align-items-center">
                
                <label>DATA DE NASCIMENTO</label>
                <input type="text" name="cpf" class="form-control" value="${dataNascimentoString}" placeholder="Digite a data de nascimento do cliente..." onkeypress="$(this).mask('00/00/0000');"  required/>
            </div>
            
             
            <div>
                <button type="submit" class="btn btn-success">Salvar</button>
                <button type="reset" class="btn btn-warning">Resetar dados</button>
                <a href="${pageContext.request.contextPath}/client" role="button" class="btn btn-danger">Cancelar</a>
            </div>
        </form> 
    </div>
</div>