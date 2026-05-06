<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="jakarta.servlet.http.HttpSession"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%
    HttpSession sesion = request.getSession(false);
    if (sesion == null || sesion.getAttribute("usuario") == null) {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Conversión de Unidades</title>
    <!-- Fuente -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600;700&display=swap" rel="stylesheet">
    <!-- CSS (con bust de caché) -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles.css?v=5">
</head>
<body class="main-bg">

<header class="topbar">
    <h1>Conversiones de Unidades</h1>
    <div class="user-badge">
        <div class="user-info">
            <span>Conectado como</span>
            <strong><%= sesion.getAttribute("usuario") %></strong>
        </div>
        <form action="<%= request.getContextPath() %>/index.jsp" method="get" style="margin: 0;">
            <button type="submit" class="logout">Cerrar sesión</button>
        </form>
    </div>
</header>

<!-- ======= Shell (desktop: 2 columnas / mobile: 1) ======= -->
<div class="conv-shell">

    <!-- data-* sirve para rehidratar tras el POST -->
    <main class="conv-wrapper"
          data-cat="${param.cat}"
          data-tipo="${param.tipo}"
          data-valor="${param.valor}">

        <!-- Paso 1 -->
        <section class="conv-card">
            <div class="conv-card__header header--blue">
                <span class="header-icon" aria-hidden="true">i</span>
                <h2>Categoría de Conversión</h2>
            </div>
            <div class="conv-card__body">
                <div class="input-outline">
                    <select id="categoria" aria-label="Seleccionar Categoría">
                        <option value="" selected>Seleccionar Categoría</option>
                        <option value="longitud">Longitud</option>
                        <option value="temperatura">Temperatura</option>
                        <option value="masa">Masa</option>
                    </select>
                </div>
            </div>
        </section>

        <!-- Paso 2 -->
        <section class="conv-card">
            <div class="conv-card__header header--orange">
                <span class="header-icon" aria-hidden="true">i</span>
                <h2>Tipo de Conversión</h2>
            </div>
            <div class="conv-card__body">
                <div class="input-outline">
                    <select id="tipo" aria-label="Seleccionar tipo de conversión" disabled>
                        <option value="">Primero seleccione una</option>
                    </select>
                </div>
            </div>
        </section>

        <!-- Paso 3 -->
        <form id="form-conversion" class="conv-card"
              action="<%= request.getContextPath() %>/convertir" method="post" novalidate>

            <div class="conv-card__header header--green">
                <span class="header-icon" aria-hidden="true">i</span>
                <h2>Ingrese el Valor</h2>
            </div>

            <div class="conv-card__body">
                <!-- Hidden para enviar lo seleccionado -->
                <input type="hidden" name="tipo" id="tipo-hidden">
                <input type="hidden" name="cat" id="cat-hidden">

                <div class="input-outline">
                    <input type="number" inputmode="decimal" id="valor" name="valor"
                           placeholder="Ingrese el valor numérico" required>
                </div>

                <button type="submit" class="btn-primary btn-block">CONVERTIR</button>

                <!-- Resultado para móviles -->
                <c:if test="${not empty param.resultado}">
                    <div class="result-chip only-mobile">
                        <strong>${param.resultado}</strong>
                        <small>Valor inicial: ${param.valor} · Operación: ${param.tipo}</small>
                    </div>
                </c:if>
                <c:if test="${not empty param.error}">
                    <p class="alert only-mobile">${param.error}</p>
                </c:if>
            </div>
        </form>
    </main>

    <!-- Panel de resultado (solo desktop) -->
    <aside class="result-panel only-desktop">
        <h3>Resultado</h3>
        <c:choose>
            <c:when test="${not empty param.resultado}">
                <p class="result-big">${param.resultado}</p>
                <p class="muted">Valor inicial: <b>${param.valor}</b></p>
                <p class="muted">Operación: <b>${param.tipo}</b></p>
            </c:when>
            <c:when test="${not empty param.error}">
                <p class="alert">${param.error}</p>
            </c:when>
            <c:otherwise>
                <p class="muted">Ingresa un valor y presiona <b>Convertir</b>.</p>
            </c:otherwise>
        </c:choose>
    </aside>
</div>

<!-- FAB limpiar -->
<button type="button" class="fab danger" id="btn-limpiar" title="Limpiar">
    <span class="trash" aria-hidden="true">🗑️</span>
</button>

<script>
    // Opciones por categoría
    const opciones = {
        longitud: [
            { val: "cmToIn", txt: "Centímetros → Pulgadas", min0: true },
            { val: "inToCm", txt: "Pulgadas → Centímetros", min0: true }
        ],
        temperatura: [
            { val: "cToF", txt: "Celsius → Fahrenheit", min0: false },
            { val: "fToC", txt: "Fahrenheit → Celsius", min0: false }
        ],
        masa: [
            { val: "kgToLb", txt: "Kilogramos → Libras", min0: true },
            { val: "lbToKg", txt: "Libras → Kilogramos", min0: true }
        ]
    };

    // DOM
    const categoria = document.getElementById('categoria');
    const tipo = document.getElementById('tipo');
    const tipoHidden = document.getElementById('tipo-hidden');
    const catHidden = document.getElementById('cat-hidden');
    const valor = document.getElementById('valor');
    const form = document.getElementById('form-conversion');
    const btnLimpiar = document.getElementById('btn-limpiar');

    // Listeners
    categoria.addEventListener('change', () => {
        const cat = categoria.value;
        catHidden.value = cat || '';
        tipo.innerHTML = '';
        if (!cat) {
            tipo.disabled = true;
            tipo.innerHTML = '<option value="">Primero seleccione una</option>';
            tipoHidden.value = '';
            return;
        }
        opciones[cat].forEach(o => {
            const opt = document.createElement('option');
            opt.value = o.val; opt.textContent = o.txt;
            tipo.appendChild(opt);
        });
        tipo.disabled = false;
        aplicarReglas(opciones[cat][0].min0);
        tipoHidden.value = tipo.value;
    });

    tipo.addEventListener('change', () => {
        const cat = categoria.value;
        const sel = opciones[cat]?.find(o => o.val === tipo.value);
        aplicarReglas(sel ? sel.min0 : true);
        tipoHidden.value = tipo.value || '';
    });

    function aplicarReglas(min0) {
        if (min0) { valor.setAttribute('min','0'); }
        else { valor.removeAttribute('min'); }
    }

    form.addEventListener('submit', (e) => {
        if (!categoria.value) { e.preventDefault(); toast("Seleccione una categoría."); return; }
        if (!tipo.value) { e.preventDefault(); toast("Seleccione el tipo de conversión."); return; }
        if (valor.value.trim()==='' || !isFinite(parseFloat(valor.value))) {
            e.preventDefault(); toast("Ingrese un valor numérico válido."); return;
        }
        tipoHidden.value = tipo.value;
        catHidden.value = categoria.value;
    });

    btnLimpiar.addEventListener('click', () => {
        categoria.value=''; tipo.innerHTML='<option value="">Primero seleccione una</option>';
        tipo.disabled=true; valor.value=''; tipoHidden.value=''; catHidden.value='';
        toast("Formulario reiniciado.");
    });

    // Rehidratar tras POST (mantener selección)
    (function rehidratar(){
        const wrapper = document.querySelector('.conv-wrapper');
        let cat = (wrapper.dataset.cat || '').trim();
        const tpo = (wrapper.dataset.tipo || '').trim();
        const val = (wrapper.dataset.valor || '').trim();

        // Inferir cat por tipo si no vino
        if (!cat && tpo){
            if (/cmToIn|inToCm/.test(tpo)) cat = 'longitud';
            else if (/cToF|fToC/.test(tpo)) cat = 'temperatura';
            else if (/kgToLb|lbToKg/.test(tpo)) cat = 'masa';
        }

        if (cat){
            categoria.value = cat;
            categoria.dispatchEvent(new Event('change'));
            if (tpo){
                tipo.value = tpo;
                tipo.dispatchEvent(new Event('change'));
            }
        }
        if (val) valor.value = val;

        catHidden.value = categoria.value || '';
        tipoHidden.value = tipo.value || '';
    })();

    // Toast
    function toast(t) {
        let b = document.querySelector('.banner');
        if (!b) { b = document.createElement('div'); b.className='banner'; document.body.appendChild(b); }
        b.textContent = t; b.classList.add('visible'); setTimeout(()=>b.classList.remove('visible'),2200);
    }
</script>
</body>
</html>