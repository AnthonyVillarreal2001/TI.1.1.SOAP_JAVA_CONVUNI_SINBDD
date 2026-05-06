<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Conversor de Unidades - Iniciar Sesión</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="styles.css?v=7">
</head>
<body class="login-bg" style="--page-bg-image: url('<%= request.getContextPath() %>/images/sullivan.jpg');">
    <main class="login-shell">
        <section class="login-intro">
            <span class="eyebrow">Cliente Web SOAP</span>
            <h1 class="section-title">Conversor de Unidades</h1>
            <p class="section-copy">Aplicación cliente para consumir el servicio SOAP de conversión de unidades.</p>

            <div class="feature-row">
                <span>SOAP</span>
                <span>Java 21</span>
                <span>Interfaz renovada</span>
            </div>
        </section>

        <section class="login-panel">
            <div class="panel-header">
                <span class="panel-mark">MC</span>
                <div>
                    <p class="panel-kicker">Acceso seguro</p>
                    <h2 class="panel-title">Iniciar sesión</h2>
                </div>
            </div>

            <form action="login" method="post" novalidate class="login-form">
                <label class="field">
                    <span>Usuario</span>
                    <div class="field-shell">
                        <input type="text" id="usuario" name="usuario" placeholder="Usuario" autocomplete="username" required>
                    </div>
                </label>

                <label class="field">
                    <span>Contraseña</span>
                    <div class="field-shell">
                        <input type="password" id="password" name="password" placeholder="Contraseña" autocomplete="current-password" required>
                    </div>
                </label>

                <button type="submit" class="btn-primary btn-block">INICIAR SESIÓN</button>

                <p class="note">Por favor, ingrese sus credenciales</p>

                <c:if test="${not empty sessionScope.loginError}">
                    <p class="alert">${sessionScope.loginError}</p>
                    <c:remove var="loginError" scope="session"/>
                </c:if>
            </form>
        </section>
    </main>
</body>
</html>
