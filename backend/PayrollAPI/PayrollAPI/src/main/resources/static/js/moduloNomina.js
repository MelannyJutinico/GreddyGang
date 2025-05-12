function seleccionarEmpleado(row, idEmpleado) {
    document.querySelectorAll(".tabla-empleados tr").forEach(tr => tr.classList.remove("seleccionado"));
    row.classList.add("seleccionado");
    document.getElementById("idEmpleadoSeleccionado").value = idEmpleado;
}

function filtrarEmpleados() {
    const input = document.getElementById("buscadorEmpleado");
    const filtro = input.value.toLowerCase();
    const filas = document.querySelectorAll("#tablaEmpleados tbody tr");

    filas.forEach(fila => {
        const nombre = fila.cells[1].innerText.toLowerCase();
        const id = fila.cells[0].innerText;
        fila.style.display = nombre.includes(filtro) || id.includes(filtro) ? "" : "none";
    });
}