document.addEventListener("DOMContentLoaded", function () {

	let btnImport = document.querySelector("#btnImport");
	let inputFile = document.querySelector("input[type='file']");

	btnImport.addEventListener("click", function () {

		// Não foi selecionado nenhum arquivo
		if (inputFile.files.length <= 0)
			return;

		let file = document.querySelector("input[type='file']").files[0];
     
		var data = new FormData()
		data.append("arquivo", file);

		fetch("/people-web/people", {
			method: 'POST',
			body: data,
		})
			.then(resp => resp.json())
			.then(data => insertDataTable(data))
			.catch(err => console.log(err));

	});

});

function insertDataTable(data) {

	console.log(data);

	let peoples = data.peoples;
	let tableBody = document.querySelector("#table-dados tbody");

	// clear data in table
	tableBody.innerHTML = "";

	peoples.forEach((people, index) => {
		let tableHeaderIndex = document.createElement("th");

		let tableDataNome = document.createElement("td");
		let tableDataCpf = document.createElement("td");
		let tableDataCep = document.createElement("td");
		let tableDataNumero = document.createElement("td");
		let tableDataComplemento = document.createElement("td");
		let tableDataEditar = document.createElement("td");
		let tableDataExcluir = document.createElement("td");

		tableHeaderIndex.innerHTML = index;

		tableDataNome.innerHTML = people.Name;
		tableDataCpf.innerHTML = people.Cpf;
		tableDataCep.innerHTML = people.Cep;
		tableDataNumero.innerHTML = people.AddressNumber;
		tableDataComplemento.innerHTML = people.Complement;
   
		let linkEditar = document.createElement("a");
		linkEditar.href = "#";
		linkEditar.innerText = "Editar";
		linkEditar.dataset.editar = index;

		tableDataEditar.appendChild(linkEditar);
		
		let linkExcluir = document.createElement("a");
		linkExcluir.href = "#";
		linkExcluir.innerText = "Excluir";
		linkExcluir.dataset.editar = index;
		
		tableDataExcluir.appendChild(linkExcluir);
 
		let tableRow = document.createElement("tr");
		tableRow.appendChild(tableHeaderIndex);
		tableRow.appendChild(tableDataNome);
		tableRow.appendChild(tableDataCpf);
		tableRow.appendChild(tableDataCep);
		tableRow.appendChild(tableDataNumero);
		tableRow.appendChild(tableDataComplemento);
		tableRow.appendChild(tableDataEditar);
		tableRow.appendChild(tableDataExcluir);

		tableBody.appendChild(tableRow);
	});
}