function seleccionarEmpleado(row, idEmpleado) {
    document.querySelectorAll(".tabla-empleados tr").forEach(tr => tr.classList.remove("seleccionado"));
    row.classList.add("seleccionado");
    console.log("Empleado seleccionado ID:", idEmpleado);
}

function filtrarEmpleados() {
    const input = document.getElementById("buscadorEmpleado").value.toLowerCase();
    const filas = document.querySelectorAll("#tablaEmpleados tbody tr");

    filas.forEach(fila => {
        const id = fila.cells[0].innerText.toLowerCase();
        const nombre = fila.cells[1].innerText.toLowerCase();
        fila.style.display = id.includes(input) || nombre.includes(input) ? "" : "none";
    });
}
