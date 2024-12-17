// 1 - recuperação das informações presentes no token
/**
 * Função para decodificar um token JWT.
 * @param {string} token - Token JWT.
 * @returns {Object} - Payload decodificado.
 */
function decodeToken(token) {
    const payloadBase64 = token.split('.')[1];
    const payloadDecoded = atob(payloadBase64);
    return JSON.parse(payloadDecoded);
}
const token = sessionStorage.getItem('token');
const payload = decodeToken(token);
// 1 - -----------------------------------------------------------------------------------



// 2 - Código para carregar dinamicamente na interface a imagem escolhida pelo usuario
const inputFile = document.getElementById('inputImage');
const pictureImage = document.querySelector('.spanInputImage');
const pictureImageTxt = 'Clique para escolher uma imagem';
pictureImage.innerHTML = pictureImageTxt;

inputFile.addEventListener('change', function (e) {
    const inputTarget = e.target;
    const file = inputTarget.files[0];
    if (file) {
        const reader = new FileReader();

        reader.addEventListener('load', function (e) {
            const readerTarget = e.target;

            const img = document.createElement('img');
            img.src = readerTarget.result;
            img.classList.add('chosenImage')
            pictureImage.innerHTML = '';
            pictureImage.appendChild(img);
        });

        reader.readAsDataURL(file);
    } else {
        pictureImage.innerHTML = pictureImageTxt;
    }
});
// 2 - -----------------------------------------------------------------------------------


// 3 - Formulario para criação de um produto Blueray
const apiUrl = 'http://localhost:8080/blueray';
document.getElementById('formCreateAnime').addEventListener('submit', async function (event) {
    event.preventDefault();

    const formData = new FormData();
    const imageFile = document.getElementById('inputImage').files[0];
    if (imageFile) {
        formData.append('image', imageFile);
    }

    const data = {
        nome: document.getElementById('name').value,
        descricao: document.getElementById('description').value,
        preco: document.getElementById('price').value,
        season: document.getElementById('season').value
    };
    formData.append('data', JSON.stringify(data));

    try {
        // sessionStorage.getItem('token')
        response = await fetch("http://localhost:8080/blueray", {
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${token}`
            },
            body: formData
        });


        if (!response.ok) throw new Error(`Erro: ${response.status}`);
        alert("Anime adicionado!");
        document.getElementById('formCreateAnime').reset();
        pictureImage.innerHTML = pictureImageTxt;
    } catch (error) {
        console.error('Erro ao salvar anime:', error);
    }
});
// 3 - -----------------------------------------------------------------------------------



document.addEventListener('DOMContentLoaded', () => {
    if (!sessionStorage.getItem('token')) {
        window.location.href = '../login/login.html';
    } else {
        //esta função esta presente em 'dynamicComponents'
        setupAdminInterface();
        //esta função esta presente em 'navigation'
        setupNavigation();
        //esta função esta presente em 'dynamicComponents'
        setupPopUpMenu();
    }
});