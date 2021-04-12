<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">

<title>Detalhes pessoa</title>
</head>

<body>

	<%
	models.People pessoa = (models.People) request.getAttribute("people");
	%>

	<div class="container">
		<h3 class="mt-3">Pessoa detalhes</h3>

		<hr />

		<div class="card mt-3">
			<div class="card-header">
				<strong>Detalhes</strong>
			</div>
			<div class="card-body">

				<div class="row">
					<div class="col-md-1">
						<div class="mb-3">
							<label for="id" class="form-label">#</label> <input type="text"
								class="form-control" id="id" aria-describedby="id"
								readonly="readonly" value="<%=pessoa.getId()%>">
						</div>
					</div>

					<div class="col-md-7">
						<div class="mb-3">
							<label for="nome" class="form-label">Nome</label> <input
								type="text" class="form-control" id="name"
								aria-describedby="nome" readonly="readonly"
								value="<%=pessoa.getName()%>">
						</div>
					</div>

					<div class="col-md-4">
						<div class="mb-3">
							<label for="cpf" class="form-label">CPF</label> <input
								type="text" readonly="readonly" class="form-control" id="cpf"
								aria-describedby="cpf" value="<%=pessoa.getCpf()%>">
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-3">
						<div class="mb-3">
							<label for="cep" class="form-label">CEP</label> <input
								type="text" readonly="readonly" class="form-control" id="cep"
								aria-describedby="cep" value="<%=pessoa.getAddress().getCep()%>">
						</div>
					</div>

					<div class="col-md-7">
						<div class="mb-3">
							<label for="logradouro" class="form-label">Logradouro</label> <input
								type="text" readonly="readonly" class="form-control"
								readonly="readonly" id="logradouro"
								aria-describedby="logradouro"
								value="<%=pessoa.getAddress().getStreet()%>">
						</div>
					</div>

					<div class="col-md-2">
						<div class="mb-3">
							<label for="numero" class="form-label">Número</label> <input
								type="text" class="form-control" id="addressNumber"
								readonly="readonly" aria-describedby="numero"
								value="<%=pessoa.getAddressNumber()%>">
						</div>
					</div>
				</div>

				<div class="row">

					<div class="col-md-12">
						<div class="mb-3">
							<label for="complemento" class="form-label">Complemento</label> <input
								type="text" class="form-control" id="complement"
								readonly="readonly" aria-describedby="complemento"
								value="<%=pessoa.getComplement()%>">
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4">
						<div class="mb-3">
							<label for="bairro" class="form-label">Bairro</label> <input
								type="text" class="form-control" id="bairro" readonly="readonly"
								aria-describedby="bairro"
								value="<%=pessoa.getAddress().getDistrict()%>">
						</div>
					</div>

					<div class="col-md-4">
						<div class="mb-3">
							<label for="cidade" class="form-label">Cidade</label> <input
								type="text" class="form-control" id="cidade" readonly="readonly"
								aria-describedby="cidade"
								value="<%=pessoa.getAddress().getLocality()%>">
						</div>
					</div>

					<div class="col-md-4">
						<div class="mb-3">
							<label for="estado" class="form-label">Estado</label> <input
								type="text" class="form-control" id="estado" readonly="readonly"
								aria-describedby="estado"
								value="<%=pessoa.getAddress().getUf()%>">
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row mt-3">
			<div class="col-md-12 text-end">
				<a href="/people-web" class="btn btn-outline-primary">Voltar</a>
			</div>
		</div>
</body>

</html>