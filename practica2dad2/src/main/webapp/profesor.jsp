<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>CRUD Profesor</title>
	<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript">
		function load(id, nombre, apellido) {
			var existente = document.getElementById('prof_' + id);
			if (existente) existente.remove();

			var entry = document.createElement('li');
			entry.id = 'prof_' + id;

			var aEditar = document.createElement('a');
			aEditar.appendChild(document.createTextNode(" [Editar]"));
			aEditar.href = "#";
			aEditar.onclick = function (e) {
				e.preventDefault();
				$('#id').val(id);
				$('#nombre').val(nombre);
				$('#apellido').val(apellido);
			};

			var aBorrar = document.createElement('a');
			aBorrar.appendChild(document.createTextNode(" [Borrar]"));
			aBorrar.href = "#";
			aBorrar.onclick = function (e) {
				e.preventDefault();
				$.ajax({
					url: 'rest/profesor/' + id,
					type: 'DELETE',
					dataType: "json",
					success: function (result) {
						document.getElementById('prof_' + id).remove();
					},
					error: function () { alert('Error al borrar el profesor'); }
				});
			};

			entry.appendChild(document.createTextNode("(" + id + ") " + nombre + " " + apellido));
			entry.appendChild(aEditar);
			entry.appendChild(aBorrar);
			$('#profesores').append(entry);
		}

		$(document).ready(function () {
			$("#crearProfesor").click(function () {
				var info = { nombre: $('#nombre').val(), apellido: $('#apellido').val() };
				$.ajax({
					data: JSON.stringify(info),
					url: 'rest/profesor',
					headers: { 'Accept': 'application/json', 'Content-Type': 'application/json' },
					type: 'POST',
					dataType: "json",
					success: function (result) {
						load(result.profesor.id, result.profesor.nombre, result.profesor.apellido);
						$('#nombre').val('');
						$('#apellido').val('');
					},
					error: function () { alert('Error al crear el profesor'); }
				});
			});

			$("#actualizarProfesor").click(function () {
				var info = { id: parseInt($('#id').val()), nombre: $('#nombre').val(), apellido: $('#apellido').val() };
				$.ajax({
					data: JSON.stringify(info),
					url: 'rest/profesor',
					headers: { 'Accept': 'application/json', 'Content-Type': 'application/json' },
					type: 'PUT',
					dataType: "json",
					success: function (result) {
						load(result.profesor.id, result.profesor.nombre, result.profesor.apellido);
						$('#id').val('');
						$('#nombre').val('');
						$('#apellido').val('');
					},
					error: function () { alert('Error al actualizar'); }
				});
			});

			$.ajax({
				url: 'rest/profesor/listado',
				type: 'GET',
				dataType: "json",
				success: function (result) {
					if (result.profesores) {
						jQuery.each(result.profesores, function (i, val) {
							load(val.id, val.nombre, val.apellido);
						});
					}
				}
			});
		});
	</script>
</head>
<body>
	<h1>CRUD Profesores</h1>
	<p><a href="index.jsp">&laquo; Volver atrás</a></p>
	<br>
	<b>Formulario para gestionar profesores:</b><br>
	<input type="hidden" id="id">
	Nombre: <input type="text" id="nombre"><br>
	Apellido: <input type="text" id="apellido"><br>
	<button id="crearProfesor">Crear</button>
	<button id="actualizarProfesor">Actualizar</button>
	<br><br>
	<b>Listado de profesores:</b>
	<ul id="profesores"></ul>
</body>
</html>