<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestionar Asignaturas</title>
<meta charset="UTF-8">
<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">

		function load(id, nombre, creditos) {
			var existente = document.getElementById(id);
			if (existente) existente.remove();

			var entry = document.createElement('li');

			var aEditar = document.createElement('a');
			var linkEditar = document.createTextNode(" [Editar]");
			aEditar.appendChild(linkEditar);
			aEditar.onclick = function () {
				$('#id').val(id);
				$('#nombre').val(nombre);
				$('#creditos').val(creditos);
			};

			var aBorrar = document.createElement('a');
			var linkBorrar = document.createTextNode(" [Borrar]");
			aBorrar.appendChild(linkBorrar);
			aBorrar.onclick = function () {
				$.ajax({
					url: 'rest/asignatura/' + id,
					type: 'DELETE',
					dataType: "json",
					success: function (result) {
						document.getElementById(id).remove();
					},
					error: function (jqXhr, textStatus, errorMessage) {
						//TODO : mejora en la vixualizaciï¿½n de errores
						/* Poner mensaje de error devuelto por el backend */
						alert('error');
					}
				});
			};

			entry.id = id;
			entry.appendChild(document.createTextNode("(" + id + ") " + nombre + " - " + creditos));
			entry.appendChild(aEditar);
			entry.appendChild(aBorrar);

			$('#asignaturas').append(entry);
		}

		$(document).ready(function () {

			$("#crearAsignatura").click(function () {
				var asignaturaInfo = { nombre: $('#nombre').val(), creditos: parseInt($('#creditos').val()) };

				$.ajax({
					data: JSON.stringify(asignaturaInfo),
					url: 'rest/asignatura/',
					headers: {
						'Accept': 'application/json',
						'Content-Type': 'application/json'
					},
					type: 'POST',
					dataType: "json",
					success: function (result) {
						console.log(result);
						load(result.asignatura.id, result.asignatura.nombre, result.asignatura.creditos);
						$('id').val('');
						$('#nombre').val('');
						$('#creditos').val('');
					},
					error: function (jqXhr, textStatus, errorMessage) {
						//TODO : mejora en la vixualizaciï¿½n de errores
						/* Poner mensaje de error devuelto por el backend */
						alert('Error al crear');
					}
				});
			});

			$("#actualizarAsignatura").click(function () {
				var asignaturaInfo = { id: parseInt($('#id').val()), nombre: $('#nombre').val(), creditos: parseInt($('#creditos').val()) };

				$.ajax({
					data: JSON.stringify(asignaturaInfo),
					url: 'rest/asignatura',
					headers: {
						'Accept': 'application/json',
						'Content-Type': 'application/json'
					},
					type: 'PUT',
					dataType: "json",
					success: function (result) {
						console.log(result);
						load(result.asignatura.id, result.asignatura.nombre, result.asignatura.creditos);
						$('#id').val('');
						$('#nombre').val('');
						$('#creditos').val('');
					},
					error: function (jqXhr, textStatus, errorMessage) {
						//TODO : mejora en la vixualizaciï¿½n de errores
						/* Poner mensaje de error devuelto por el backend */
						alert('Error al actualizar');
					}
				});
			});

			$.ajax({
				url: 'rest/asignatura/listado',
				type: 'GET',
				dataType: "json",
				success: function (result) {
					if (result.asignaturas) {
						jQuery.each(result.asignaturas, function (i, val) {
							load(val.id, val.nombre, val.creditos);
						});
					}
				}
			});
		});

	</script>
</head>
<body>
	<h1>Asignaturas</h1>
	<br>
	<a href="index.jsp">Volver atrás</a>
	<br><br>
	Formulario para gestionar asignaturas<br>
	
	Id:<input type=text id="id" readonly><br>
	Nombre:<input type=text id="nombre"><br>
	Creditos:<input type=number id="creditos"><br>
	<button id="crearAsignatura">Crear</button>
	<button id="actualizarAsignatura">Actualizar</button>

	<br>
	Listado de asignaturas
	<br>
	<ul id="asignaturas">
	</ul>
</body>
</html>
