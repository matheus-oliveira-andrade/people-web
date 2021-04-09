var modal = new bootstrap.Modal(document.querySelector("#modalEdicao"), null);

document.addEventListener("DOMContentLoaded", function () {

	let btnImport = document.querySelector("#btnImport");
	let btnClearSession = document.querySelector("#btnClearSession");
	let modalEdit = document.getElementById("modalEdicao");
	let btnModalEdit = document.getElementById("btnSalvarEdicao");
	let btnLoadbyId = document.getElementById("btnCarregar");

	btnImport.addEventListener("click", importFile);

	btnClearSession.addEventListener("click", clearSession);
 
	modalEdit.addEventListener("hidden.bs.modal", function (event) {
		let idInput = document.getElementById("id");
		let nameInput = document.getElementById("name");
		let cpfInput = document.getElementById("cpf");
		let cepInput = document.getElementById("cep");
		let addressnumberInput = document.getElementById("addressNumber");
		let complementInput = document.getElementById("complement");

		idInput.value = '';
		nameInput.value = '';
		cpfInput.value = '';
		cepInput.value = '';
		addressnumberInput.value = '';
		complementInput.value = '';
	})

	btnModalEdit.addEventListener("click", editData);

	btnLoadbyId.addEventListener("click", loadDataById)

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

function loadDataById() {

	let idDocument = document.getElementById("idDocumento").value;

	// dont have value
	if (!idDocument)
		return;

	fetch("/people-web/people?idDocument=" + idDocument, {
		method: 'GET',
	})
		.then(resp => {
			return resp.json();
		})
		.then(data => insertDataInTable(data))
		.catch(err => console.log(err));
}

function importFile(e) {

	e.preventDefault();

	let inputFile = document.querySelector("input[type='file']");

	// Not selected file
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

				insertDataInTable(data);

				document.getElementById("peloID-tab").dispatchEvent(new Event("click"));
				document.getElementById("idDocumento").value = data.id;
			}
			else {
				console.log(data.message);
			}
		})
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
		linkEditar.dataset.name = people.name;
		linkEditar.dataset.cpf = people.cpf;
		linkEditar.dataset.cep = people.cep;
		linkEditar.dataset.addressnumber = people.addressNumber;
		linkEditar.dataset.complement = people.complement;

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
	linksEditar.forEach(element => element.addEventListener("click", setDataModal));
	linksExcluir.forEach(element => element.addEventListener("click", deleteData));
}

function setDataModal(e) {
	modal.show();

	let data = e.target.dataset;

	let idInput = document.getElementById("id");
	let nameInput = document.getElementById("name");
	let cpfInput = document.getElementById("cpf");
	let cepInput = document.getElementById("cep");
	let addressnumberInput = document.getElementById("addressNumber");
	let complementInput = document.getElementById("complement");

	idInput.value = data.id;
	nameInput.value = data.name;
	cpfInput.value = data.cpf;
	cepInput.value = data.cep;
	addressnumberInput.value = data.addressnumber;
	complementInput.value = data.complement;

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

	let id = document.getElementById("id").value;
	let name = document.getElementById("name").value;
	let cpf = document.getElementById("cpf").value;
	let cep = document.getElementById("cep").value;
	let addressNumber = document.getElementById("addressNumber").value;
	let complement = document.getElementById("complement").value;

	if (!id || !name || !cpf || !cep || !addressNumber || !complement) {
		alert("Nenhum campo deve ficar em branco");
		return;
	}

	let data = {
		id,
		name,
		cpf,
		cep,
		addressNumber,
		complement,
	};

	fetch("/people-web/people", {
		method: 'PUT',
		body: JSON.stringify(data),
	})
		.then(resp => {
			return resp.json();
		})
		.then(data => {
			if (data.success) {
				console.log("Editado com sucesso");

				modal.hide();

				// reload grid
				loadData();
			}
			else {
				console.log(data.message);
			}
		})
		.catch(err => console.log(err));
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