<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Asignar Profesores</title>
	<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript">
		
        function cargarProfesoresDeAsignatura(idAsignatura) {
            $('#listaAsignaciones').empty();
            if(!idAsignatura) return;

            $.ajax({
				url: 'rest/asignacion/' + idAsignatura,
				type: 'GET',
				dataType: "json",
				success: function (result) {
					if (result.profesoresIds) {
						jQuery.each(result.profesoresIds, function (i, profId) {
                            var entry = document.createElement('li');
                            entry.appendChild(document.createTextNode("Profesor ID: " + profId + " "));
                            
                            var btnRemover = document.createElement('button');
                            btnRemover.innerHTML = "Eliminar asignación";
                            btnRemover.onclick = function() {
                                eliminarAsignacion(idAsignatura, profId);
                            };

                            entry.appendChild(btnRemover);
                            $('#listaAsignaciones').append(entry);
						});
					} else {
                        $('#listaAsignaciones').append("<li>No hay profesores asignados.</li>");
                    }
				}
			});
        }

        function eliminarAsignacion(idAsig, idProf) {
            $.ajax({
				url: 'rest/asignacion/' + idAsig + '/profesor/' + idProf,
				type: 'DELETE',
				dataType: "json",
				success: function (result) {
					cargarProfesoresDeAsignatura(idAsig);
				},
				error: function (jqXhr) {
					alert('Error al desasignar profesor');
				}
			});
        }

		$(document).ready(function () {
			// Cargar desplegable de Asignaturas
			$.ajax({
				url: 'rest/asignatura/listado',
				type: 'GET',
				dataType: "json",
				success: function (result) {
					if (result.asignatura) {
						jQuery.each(result.asignatura, function (i, val) {
							$('#selectAsignatura').append(new Option(val.nombre, val.id));
						});
					}
				}
			});

            // Cargar desplegable de Profesores
            $.ajax({
				url: 'rest/profesor/listado',
				type: 'GET',
				dataType: "json",
				success: function (result) {
					if (result.profesores) {
						jQuery.each(result.profesores, function (i, val) {
							$('#selectProfesor').append(new Option(val.nombre + " " + val.apellido, val.id));
						});
					}
				}
			});

            // Cuando cambie la asignatura seleccionada, buscar sus profesores
            $('#selectAsignatura').change(function() {
                cargarProfesoresDeAsignatura($(this).val());
            });

            // Acción para asignar el profesor seleccionado a la asignatura seleccionada
            $('#btnAsignar').click(function() {
                var idAsig = $('#selectAsignatura').val();
                var idProf = $('#selectProfesor').val();

                if(idAsig && idProf) {
                    $.ajax({
                        url: 'rest/asignacion/' + idAsig + '/profesor/' + idProf,
                        type: 'POST',
                        dataType: "json",
                        success: function (result) {
                            cargarProfesoresDeAsignatura(idAsig);
                        },
                        error: function (jqXhr) {
                            alert('Conflicto: El profesor ya está asignado o ocurrió un error.');
                        }
                    });
                } else {
                    alert("Selecciona asignatura y profesor");
                }
            });
		});
	</script>
</head>
<body>
	<h1>Asignar Profesores a Asignaturas</h1>
	<p><a href="index.jsp">&laquo; Volver</a></p>

    <hr>
    <h3>Formulario de Asignación</h3>
    <label>Asignatura:</label>
    <select id="selectAsignatura">
        <option value="">-- Selecciona Asignatura --</option>
    </select>
    <br><br>
    
    <label>Profesor:</label>
    <select id="selectProfesor">
        <option value="">-- Selecciona Profesor --</option>
    </select>
    <br><br>

    <button id="btnAsignar">Vincular Profesor</button>

    <hr>
    <h3>Profesores Asignados a la Asignatura Seleccionada</h3>
    <ul id="listaAsignaciones">
        <li>Seleccione una asignatura para ver sus profesores.</li>
    </ul>

</body>
</html>