function seleccionarEmpleado(row, idEmpleado) {
    document.querySelectorAll(".tabla-empleados tr").forEach(tr => tr.classList.remove("seleccionado"));
    row.classList.add("seleccionado");
    document.getElementById("idEmpleadoSeleccionado").value = idEmpleado; // para novedades
    document.getElementById("idEmpleadoSeleccionadoConcepto").value = idEmpleado; // para conceptos
    document.getElementById("idEmpleadoSeleccionadoHoras").value = idEmpleado; // para horas extra


    document.getElementById("form-novedades").removeAttribute("disabled");
    document.getElementById("form-conceptos").removeAttribute("disabled");
    document.getElementById("form-horas-extra").removeAttribute("disabled");

    document.getElementById("msg-novedades")?.remove();
    document.getElementById("msg-conceptos")?.remove();
    document.getElementById("msg-horas-extra")?.remove();


    const periodoId = document.querySelector('input[name="idPeriodo"]').value;

    fetch(`/novedad/listar?idEmpleado=${idEmpleado}&idPeriodo=${periodoId}`)
        .then(response => response.json())
        .then(data => {
            const tabla = document.querySelector(".tabla-novedad tbody");
            tabla.innerHTML = "";

            if (data.length === 0) {
                tabla.innerHTML = "<tr><td colspan='5'>No hay novedades registradas.</td></tr>";
                return;
            }

            data.forEach(n => {
                const fila = `<tr>
                    <td>${n.tipoNovedad}</td>
                    <td>${n.fechaInicio}</td>
                    <td>${n.fechaFin}</td>
                    <td>${n.observacion || ''}</td>
                     <td><button class="btn-accion" onclick="eliminarNovedad(${n.idNovedad})">Eliminar</button></td>
                </tr>`;
                tabla.insertAdjacentHTML("beforeend", fila);
            });
        });

    fetch(`/concepto/listar?idEmpleado=${idEmpleado}&idPeriodo=${periodoId}`)
        .then(resp => resp.json())
        .then(data => {
            const tabla = document.querySelector(".tabla-concepto tbody");
            tabla.innerHTML = "";

            if (data.length === 0) {
                tabla.innerHTML = "<tr><td colspan='4'>No hay conceptos registrados.</td></tr>";
                return;
            }

            data.forEach(item => {
                const fila = `<tr>
                <td>${item.tipo}</td>
                <td>${item.nombre}</td>
                <td>${item.valor}</td>
            </tr>`;
                tabla.insertAdjacentHTML("beforeend", fila);
            });
        });

    fetch(`/concepto/listar-horas-extra?idEmpleado=${idEmpleado}&idPeriodo=${periodoId}`)
        .then(res => res.json())
        .then(data => {
            const tabla = document.querySelector(".tabla-horaextra tbody");
            tabla.innerHTML = "";

            if (data.length === 0) {
                tabla.innerHTML = "<tr><td colspan='4'>No hay horas extra registradas.</td></tr>";
                return;
            }

            data.forEach(item => {
                const fila = `<tr>
                <td>${item.conceptoHoraExtra}</td>
                <td>${item.horas}</td>
                <td>${item.valorHora}</td>
                <td>${item.valorTotal}</td>
            </tr>`;
                tabla.insertAdjacentHTML("beforeend", fila);
            });
        });


}

// Hacer la función global
window.seleccionarEmpleado = seleccionarEmpleado;


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


function eliminarNovedad(idNovedad) {
    Swal.fire({
        title: '¿Estás seguro?',
        text: 'Esta acción eliminará la novedad seleccionada.',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#aaa',
        confirmButtonText: 'Sí, eliminar',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.isConfirmed) {
            document.getElementById("idNovedadEliminar").value = idNovedad;
            document.getElementById("formEliminarNovedad").submit();
        }
    });
}





