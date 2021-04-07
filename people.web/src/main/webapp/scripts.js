document.addEventListener("DOMContentLoaded", function () {
 
	let btnImport = document.querySelector("#btnImport");
	let btnClearSession = document.querySelector("#btnClearSession");
	let inputFile = document.querySelector("input[type='file']");

	btnImport.addEventListener("click", function (e) {
		e.preventDefault();

		// Não foi selecionado nenhum arquivo
		if (inputFile.files.length <= 0)
			return;

		let file = document.querySelector("input[type='file']").files[0];

		var data = new FormData()
		data.append("arquivo", file);

		fetch("/people-web/file", {
			method: 'POST',
			body: data,
		})
			.then(resp => {
				return resp.json();
			})
			.then(data => {
				if (data.success) {
					console.log("Arquivo lido com sucesso");
					console.log("id documento " + data.id);

					insertDataInTable(data)
				}
				else {
					console.log(data.message);
				}
			})
			.catch(err => console.log(err));
	});

	btnClearSession.addEventListener("click", clearSession);

	// get data in server on load of page
	loadData();
});

function loadData() {
	fetch("/people-web/people", {
		method: 'GET',
	})
		.then(resp => {
			return resp.json();
		})
		.then(data => insertDataInTable(data))
		.catch(err => console.log(err));
}

function insertDataInTable(data) {
	let peoples = data.peoples;
	let tableBody = document.querySelector("#table-dados tbody");

	// don't have data
	if (!peoples || peoples.length <= 0) {
		tableBody.innerHTML = "";
		document.querySelector("#btnClearSession").style.visibility = 'hidden';
		return;
	} else
		document.querySelector("#btnClearSession").style.visibility = 'visible';

	// clear data in table
	tableBody.innerHTML = "";

	peoples.forEach((people, index) => {
		let tableHeaderId = document.createElement("th");
		let tableDataNome = document.createElement("td");
		let tableDataCpf = document.createElement("td");
		let tableDataCep = document.createElement("td");
		let tableDataNumero = document.createElement("td");
		let tableDataComplemento = document.createElement("td");
		let tableDataEditar = document.createElement("td");
		let tableDataExcluir = document.createElement("td");

		tableHeaderId.innerHTML = people.id;
		tableDataNome.innerHTML = people.name;
		tableDataCpf.innerHTML = people.cpf;
		tableDataCep.innerHTML = people.cep;
		tableDataNumero.innerHTML = people.addressNumber;
		tableDataComplemento.innerHTML = people.complement;

		let linkEditar = document.createElement("a");
		linkEditar.href = "#";
		linkEditar.innerText = "Editar";
		linkEditar.innerText = "Editar";
		linkEditar.setAttribute("name", "linkEditar");
		linkEditar.dataset.id = people.id;

		tableDataEditar.appendChild(linkEditar);

		let linkExcluir = document.createElement("a");
		linkExcluir.href = "#";
		linkExcluir.innerText = "Excluir";
		linkExcluir.setAttribute("name", "linkExcluir");
		linkExcluir.dataset.id = people.id;

		tableDataExcluir.appendChild(linkExcluir);

		let tableRow = document.createElement("tr");
		tableRow.appendChild(tableHeaderId);
		tableRow.appendChild(tableDataNome);
		tableRow.appendChild(tableDataCpf);
		tableRow.appendChild(tableDataCep);
		tableRow.appendChild(tableDataNumero);
		tableRow.appendChild(tableDataComplemento);
		tableRow.appendChild(tableDataEditar);
		tableRow.appendChild(tableDataExcluir);

		tableBody.appendChild(tableRow);
	});

	// Set event for each row in table 
	let linksEditar = document.querySelectorAll("a[name='linkEditar']");
	let linksExcluir = document.querySelectorAll("a[name='linkExcluir']");
	linksEditar.forEach(element => element.addEventListener("click", editData));
	linksExcluir.forEach(element => element.addEventListener("click", deleteData));
}

function deleteData(e) {

	let id = e.target.dataset.id;

	var data = new FormData()
	data.append("id", id);

	fetch("/people-web/people", {
		method: 'DELETE',
		body: JSON.stringify({ id: id }),
	})
		.then(resp => {
			return resp.json();
		})
		.then(data => {
			if (data.success) {
				console.log("Excluído com sucesso");

				// reload grid
				loadData();
			}
			else {
				console.log(data.message);
			}
		})
		.catch(err => console.log(err));


}

function editData(e) {
	console.log(e.target.dataset.id);
}

function clearSession() {
	fetch("/people-web/session", {
		method: 'DELETE',
	})
		.then(() => {
			loadData();
		})
		.catch(err => console.log(err));
}