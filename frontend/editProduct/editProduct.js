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

var anime;
const urlParams = new URLSearchParams(window.location.search);
const animeId = urlParams.get('idAnime');
const imgProduct = document.getElementById('imgProduct');

fetch(`http://localhost:8080/blueray/${animeId}`)
    .then(response => {
        if (!response.ok) {
            throw new Error('Erro na rede ao tentar acessar o endpoint');
        }
        return response.json();
    })
    .then(animeAtual => {
        const name = document.getElementById('name');
        const price = document.getElementById('price');
        const season = document.getElementById('season');
        const description = document.getElementById('description');

        anime = animeAtual;
        name.value = animeAtual.nome;
        price.value = animeAtual.preco;
        season.value = animeAtual.season;
        description.textContent = animeAtual.descricao;

        const img = document.createElement('img');
        img.src = animeAtual.imagem;
        img.classList.add('chosenImage')
        spanInputImage.innerHTML = '';
        spanInputImage.appendChild(img);

    });

// 2 - Código para carregar dinamicamente na interface a imagem escolhida pelo usuario
const inputFile = document.getElementById('inputImage');
const spanInputImage = document.querySelector('.spanInputImage');
const spanInputImageTxt = 'Clique para escolher uma imagem';

spanInputImage.innerHTML = spanInputImageTxt;

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
            spanInputImage.innerHTML = '';
            spanInputImage.appendChild(img);
        });

        reader.readAsDataURL(file);
    } else {
        spanInputImage.innerHTML = spanInputImageTxt;
    }
});
// 2 - -----------------------------------------------------------------------------------






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