<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>CRUD Titulacion</title>
	<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript">

		function load(id, nombre, facultad) {
			var existente = document.getElementById(id);
			if (existente) existente.remove();

			var entry = document.createElement('li');

			var aEditar = document.createElement('a');
			var linkEditar = document.createTextNode(" [Editar]");
			aEditar.appendChild(linkEditar);
			aEditar.onclick = function () {
				$('#id').val(id);
				$('#nombre').val(nombre);
				$('#facultad').val(facultad);
			};

			var aBorrar = document.createElement('a');
			var linkBorrar = document.createTextNode(" [Borrar]");
			aBorrar.appendChild(linkBorrar);
			aBorrar.onclick = function () {
				$.ajax({
					url: 'rest/titulacion/' + id,
					type: 'DELETE',
					dataType: "json",
					success: function (result) {
						document.getElementById(id).remove();
					},
					error: function (jqXhr, textStatus, errorMessage) {
						//TODO : mejora en la vixualizaci�n de errores
						/* Poner mensaje de error devuelto por el backend */
						alert('error');
					}
				});
			};

			entry.id = id;
			entry.appendChild(document.createTextNode("(" + id + ") " + nombre + " - " + facultad));
			entry.appendChild(aEditar);
			entry.appendChild(aBorrar);

			$('#titulaciones').append(entry);
		}

		$(document).ready(function () {

			$("#crearTitulacion").click(function () {
				var titulacionInfo = { nombre: $('#nombre').val(), facultad: $('#facultad').val() };

				$.ajax({
					data: JSON.stringify(titulacionInfo),
					url: 'rest/titulacion',
					headers: {
						'Accept': 'application/json',
						'Content-Type': 'application/json'
					},
					type: 'POST',
					dataType: "json",
					success: function (result) {
						console.log(result);
						load(result.titulacion.id, result.titulacion.nombre, result.titulacion.facultad);
						$('#nombre').val('');
						$('#facultad').val('');
					},
					error: function (jqXhr, textStatus, errorMessage) {
						//TODO : mejora en la vixualizaci�n de errores
						/* Poner mensaje de error devuelto por el backend */
						alert('Error al crear');
					}
				});
			});

			$("#actualizarTitulacion").click(function () {
				var titulacionInfo = { id: parseInt($('#id').val()), nombre: $('#nombre').val(), facultad: $('#facultad').val() };

				$.ajax({
					data: JSON.stringify(titulacionInfo),
					url: 'rest/titulacion',
					headers: {
						'Accept': 'application/json',
						'Content-Type': 'application/json'
					},
					type: 'PUT',
					dataType: "json",
					success: function (result) {
						console.log(result);
						load(result.titulacion.id, result.titulacion.nombre, result.titulacion.facultad);
						$('#id').val('');
						$('#nombre').val('');
						$('#facultad').val('');
					},
					error: function (jqXhr, textStatus, errorMessage) {
						//TODO : mejora en la vixualizaci�n de errores
						/* Poner mensaje de error devuelto por el backend */
						alert('Error al actualizar');
					}
				});
			});

			$.ajax({
				url: 'rest/titulacion/listado',
				type: 'GET',
				dataType: "json",
				success: function (result) {
					if (result.titulaciones) {
						jQuery.each(result.titulaciones, function (i, val) {
							load(val.id, val.nombre, val.facultad);
						});
					}
				}
			});
		});

	</script>

</head>

<body>
	<h1>CRUD Titulacion</h1>
	<br>
	<a href="index.jsp">Volver a Alumnos</a>
	<br><br>
	Formulario para gestionar titulaciones.<br>
	Id:<input type=text id="id" readonly><br>
	Nombre:<input type=text id="nombre"><br>
	Facultad:<input type=text id="facultad"><br>
	<button id="crearTitulacion">Crear</button>
	<button id="actualizarTitulacion">Actualizar</button>

	<br>
	Listado de titulaciones
	<br>
	<ul id="titulaciones">
	</ul>

</body>

</html>