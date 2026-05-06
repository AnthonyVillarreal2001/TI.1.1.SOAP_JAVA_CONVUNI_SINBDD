<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Conversor de Unidades - Iniciar Sesión</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body class="login-bg">
    <div class="login-container">
        <div class="login-card floating">
            <img src="${pageContext.request.contextPath}/images/sullivan.jpg" alt="Avatar" class="avatar">
            <h1>¡Bienvenido!</h1>
            <p>Conversor de Unidades</p>

            <form action="login" method="post" novalidate>
                <div class="input-group">
                    <input type="text" id="usuario" name="usuario" placeholder="Usuario" autocomplete="username" required>
                </div>
                <div class="input-group">
                    <input type="password" id="password" name="password" placeholder="Contraseña"
                           autocomplete="current-password" required>
                </div>

                <button type="submit" class="btn-login">INICIAR SESIÓN</button>

                <p class="note">Por favor, ingrese sus credenciales</p>

                <c:if test="${not empty sessionScope.loginError}">
                    <p class="alert">${sessionScope.loginError}</p>
                    <c:remove var="loginError" scope="session"/>
                </c:if>
            </form>
        </div>
    </div>
</body>
</html>
