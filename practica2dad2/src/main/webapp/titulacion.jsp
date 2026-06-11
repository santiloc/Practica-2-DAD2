<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>CRUD Titulacion</title>
	<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript">
		function load(id, nombre, facultad) {
			var existente = document.getElementById('tit_' + id);
			if (existente) existente.remove();

			var entry = document.createElement('li');
			entry.id = 'tit_' + id;

			var aEditar = document.createElement('a');
			aEditar.appendChild(document.createTextNode(" [Editar]"));
			aEditar.href = "#";
			aEditar.onclick = function (e) {
				e.preventDefault();
				$('#id').val(id);
				$('#nombre').val(nombre);
				$('#facultad').val(facultad);
			};

			var aBorrar = document.createElement('a');
			aBorrar.appendChild(document.createTextNode(" [Borrar]"));
			aBorrar.href = "#";
			aBorrar.onclick = function (e) {
				e.preventDefault();
				$.ajax({
					url: 'rest/titulacion/' + id,
					type: 'DELETE',
					dataType: "json",
					success: function (result) {
						document.getElementById('tit_' + id).remove();
					},
					error: function () {
						alert('Error al borrar la titulación');
					}
				});
			};

			entry.appendChild(document.createTextNode("(" + id + ") " + nombre + " - Facultad: " + facultad));
			entry.appendChild(aEditar);
			entry.appendChild(aBorrar);
			$('#titulaciones').append(entry);
		}

		$(document).ready(function () {
			$("#crearTitulacion").click(function () {
				var info = { nombre: $('#nombre').val(), facultad: $('#facultad').val() };
				$.ajax({
					data: JSON.stringify(info),
					url: 'rest/titulacion',
					headers: { 'Accept': 'application/json', 'Content-Type': 'application/json' },
					type: 'POST',
					dataType: "json",
					success: function (result) {
						load(result.titulacion.id, result.titulacion.nombre, result.titulacion.facultad);
						$('#nombre').val('');
						$('#facultad').val('');
					},
					error: function () { alert('Error al crear la titulación'); }
				});
			});

			$("#actualizarTitulacion").click(function () {
				var info = { id: parseInt($('#id').val()), nombre: $('#nombre').val(), facultad: $('#facultad').val() };
				$.ajax({
					data: JSON.stringify(info),
					url: 'rest/titulacion',
					headers: { 'Accept': 'application/json', 'Content-Type': 'application/json' },
					type: 'PUT',
					dataType: "json",
					success: function (result) {
						load(result.titulacion.id, result.titulacion.nombre, result.titulacion.facultad);
						$('#id').val('');
						$('#nombre').val('');
						$('#facultad').val('');
					},
					error: function () { alert('Error al actualizar'); }
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
	<h1>CRUD Titulaciones</h1>
	<p><a href="index.jsp">&laquo; Volver atrás</a></p>
	<br>
	<b>Formulario para gestionar titulaciones:</b><br>
	<input type="hidden" id="id">
	Nombre: <input type="text" id="nombre"><br>
	Facultad: <input type="text" id="facultad"><br>
	<button id="crearTitulacion">Crear</button>
	<button id="actualizarTitulacion">Actualizar</button>
	<br><br>
	<b>Listado de titulaciones:</b>
	<ul id="titulaciones"></ul>
</body>
</html>