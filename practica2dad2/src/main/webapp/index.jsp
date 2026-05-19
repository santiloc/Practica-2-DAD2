<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Universidad</title>
<style>
	* {
		margin: 0;
		padding: 0;
		box-sizing: border-box;
	}

	html, body {
		height: 100%;
		font-family: -apple-system, BlinkMacSystemFont, "SF Pro Display", "Helvetica Neue", Helvetica, Arial, sans-serif;
		-webkit-font-smoothing: antialiased;
		-moz-osx-font-smoothing: grayscale;
		color: #1d1d1f;
		background: linear-gradient(180deg, #fbfbfd 0%, #f5f5f7 100%);
	}

	.container {
		min-height: 100%;
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
		padding: 48px 24px;
	}

	.hero {
		text-align: center;
		margin-bottom: 56px;
	}

	.hero h1 {
		font-size: 56px;
		font-weight: 600;
		letter-spacing: -0.025em;
		line-height: 1.07;
		margin-bottom: 12px;
		background: linear-gradient(180deg, #1d1d1f 0%, #6e6e73 100%);
		-webkit-background-clip: text;
		-webkit-text-fill-color: transparent;
		background-clip: text;
	}

	.hero p {
		font-size: 21px;
		font-weight: 400;
		color: #6e6e73;
		letter-spacing: 0.011em;
	}

	.menu {
		display: grid;
		grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
		gap: 20px;
		width: 100%;
		max-width: 980px;
	}

	.card {
		display: flex;
		flex-direction: column;
		justify-content: space-between;
		padding: 32px 28px;
		min-height: 220px;
		background: #ffffff;
		border-radius: 20px;
		text-decoration: none;
		color: inherit;
		box-shadow: 0 4px 16px rgba(0, 0, 0, 0.04), 0 1px 2px rgba(0, 0, 0, 0.04);
		transition: transform 0.3s cubic-bezier(0.16, 1, 0.3, 1),
					box-shadow 0.3s cubic-bezier(0.16, 1, 0.3, 1);
		position: relative;
		overflow: hidden;
	}

	.card::before {
		content: "";
		position: absolute;
		inset: 0;
		background: linear-gradient(135deg, rgba(0, 113, 227, 0.06) 0%, rgba(0, 113, 227, 0) 60%);
		opacity: 0;
		transition: opacity 0.3s ease;
	}

	.card:hover {
		transform: translateY(-4px);
		box-shadow: 0 12px 32px rgba(0, 0, 0, 0.08), 0 2px 6px rgba(0, 0, 0, 0.04);
	}

	.card:hover::before {
		opacity: 1;
	}

	.card-icon {
		width: 48px;
		height: 48px;
		border-radius: 12px;
		display: flex;
		align-items: center;
		justify-content: center;
		font-size: 24px;
		margin-bottom: 20px;
		background: linear-gradient(135deg, #f5f5f7 0%, #e8e8ed 100%);
	}

	.card-icon.blue { background: linear-gradient(135deg, #0071e3 0%, #42a5f5 100%); color: #fff; }
	.card-icon.purple { background: linear-gradient(135deg, #5e5ce6 0%, #bf5af2 100%); color: #fff; }
	.card-icon.green { background: linear-gradient(135deg, #30d158 0%, #34c759 100%); color: #fff; }

	.card-title {
		font-size: 22px;
		font-weight: 600;
		letter-spacing: -0.01em;
		margin-bottom: 8px;
	}

	.card-description {
		font-size: 15px;
		color: #6e6e73;
		line-height: 1.4;
		margin-bottom: 24px;
	}

	.card-link {
		font-size: 15px;
		font-weight: 500;
		color: #0071e3;
		display: inline-flex;
		align-items: center;
		gap: 4px;
	}

	.card-link::after {
		content: "›";
		font-size: 18px;
		transition: transform 0.2s ease;
	}

	.card:hover .card-link::after {
		transform: translateX(4px);
	}

	footer {
		margin-top: 64px;
		font-size: 12px;
		color: #86868b;
		text-align: center;
	}

	@media (max-width: 600px) {
		.hero h1 { font-size: 40px; }
		.hero p { font-size: 18px; }
	}
</style>
</head>
<body>
	<div class="container">
		<div class="hero">
			<h1>Universidad</h1>
			<p>Sistema de gesti&oacute;n acad&eacute;mica</p>
		</div>

		<nav class="menu">
			<a class="card" href="titulacion.jsp">
				<div>
					<div class="card-icon blue">&#x1F393;</div>
					<div class="card-title">Titulaciones</div>
					<div class="card-description">Gestiona los grados y planes de estudio de la universidad.</div>
				</div>
				<span class="card-link">Acceder</span>
			</a>

			<a class="card" href="asignatura.jsp">
				<div>
					<div class="card-icon purple">&#x1F4DA;</div>
					<div class="card-title">Asignaturas</div>
					<div class="card-description">Administra las asignaturas asociadas a cada titulaci&oacute;n.</div>
				</div>
				<span class="card-link">Acceder</span>
			</a>

			<a class="card" href="asignacion.jsp">
				<div>
					<div class="card-icon green">&#x1F468;&#x200D;&#x1F3EB;</div>
					<div class="card-title">Asignar Profesores</div>
					<div class="card-description">Vincula profesores con las asignaturas que imparten.</div>
				</div>
				<span class="card-link">Acceder</span>
			</a>
		</nav>

		<footer>&copy; Universidad &middot; Panel de administraci&oacute;n</footer>
	</div>
</body>
</html>
