const token = sessionStorage.getItem('token');
const payload = decodeToken(token);

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




var anime;
const urlParams = new URLSearchParams(window.location.search);
const animeId = urlParams.get('idAnime');
const imgProduct = document.getElementById('imgProduct');
const name = document.getElementById('name');
const price = document.getElementById('price');
const season = document.getElementById('season');
const description = document.getElementById('description');
fetch(`http://localhost:8080/blueray/${animeId}`)
    .then(response => {
        if (!response.ok) {
            throw new Error('Erro na rede ao tentar acessar o endpoint');
        }
        return response.json();
    })
    .then(animeAtual => {
        anime = animeAtual;
        imgProduct.src = animeAtual.imagem;
        name.textContent = animeAtual.nome;
        price.textContent = animeAtual.preco;
        season.textContent = animeAtual.season;
        description.textContent = animeAtual.descricao;
    });


    // const animeId = getParameterByName('idAnime');

    // async function fetchAnimeDetails() {
    //     try {
    //         const response = await fetch(`http://localhost:8080/blueray/${animeId}`);
    //         if (!response.ok) {
    //             throw new Error('Erro na rede ao tentar acessar o endpoint');
    //         }
    
    //         const animeAtual = await response.json();
            
    //         document.getElementById('imgProduct').src = animeAtual.imagem;
    //         document.getElementById('name').textContent = animeAtual.nome;
    //         document.getElementById('price').textContent = animeAtual.preco;
    //         document.getElementById('season').textContent = animeAtual.season;
    //         document.getElementById('description').textContent = animeAtual.descricao;
    //     } catch (error) {
    //         console.error('Erro:', error);
    //         alert('Erro ao carregar os detalhes do anime.');
    //     }
    // }
    
    // fetchAnimeDetails();
    


document.addEventListener('DOMContentLoaded', () => {
    if (!sessionStorage.getItem('token')) {
        window.location.href = '../login/login.html';

    } else {
        setupAdminInterface();
        setupNavigation();
        setupPopUpMenu();
    }

});

