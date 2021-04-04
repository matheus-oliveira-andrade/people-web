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
			//headers: {
			//	"Content-Type": "multipart/form-data",
			//},
			body: data,
		})
		.then(resp => {
			
			// JSON.stringify(res.body.json())
			console.log(resp.json());
		})
		.catch(err => console.log(err));

	});

});