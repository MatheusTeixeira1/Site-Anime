document.addEventListener('DOMContentLoaded', () => {
    if (!sessionStorage.getItem('token')) {
        window.location.href = '../login/login.html';
    } else {
        setupAdminInterface();
        listNewAnimes();
        setupNavigation();
        setupPopUpMenu();
    }

});

document.getElementById('formSearchBlueray')?.addEventListener('submit', async (e) => {
    e.preventDefault();
    const animeName = document.getElementById('inputSearchBlueray').value;

    const cards = document.getElementById('cardsSearchResult');
    cards.innerHTML = '';

    const containerToRender="cardsSearchResult";
    const animes = await searchAnimeByName(animeName);
    if (animes && animes.length > 0) {
        // Do script dynamicComponents
        renderAnimes(animes, cardTemplate, containerToRender);
        window.scrollTo({
            top: document.getElementById('formSearchBlueray').offsetTop - 64,
            behavior: 'smooth'
        })
        
    } else {
        console.log('Nenhum anime encontrado.');
    }
});


async function listNewAnimes() {
    try {
        const animes = await searchAnimes(apiUrl);
        renderAnimes(animes, cardTemplate, "cardsNewAnimes");
    } catch (error) {
        console.error('Erro ao listar animes:', error);
    }
}
const cardTemplate = (anime) =>
    `
        <div class="card">
            <div class="frontPart">
                <img src="${anime.imagem}" alt="${anime.nome}">
                <h2>
                    ${anime.nome}
                </h2>
                <span>
                    R$: ${anime.preco}
                </span>
            </div>
            <div class="backPart">
                <div class="backPartInfoContainer">
                    <h3>${anime.nome}</h3>
                    <p>Temporada: ${anime.season} </p>
                    <p>
                    ${anime.descricao}
                    </p>
                </div>
                <div class="svgContainers">

                    <!-- <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="32px" height="32px">
                            <polygon
                                points="12,2 15.09,8.26 22,9.27 17,14.14 18.18,21.02 12,17.77 5.82,21.02 7,14.14 2,9.27 8.91,8.26"
                                fill="#ffa500" />
                        </svg> -->
                    <a href="">
                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="32px" height="32px">
                            <polygon
                                points="12,2 15.09,8.26 22,9.27 17,14.14 18.18,21.02 12,17.77 5.82,21.02 7,14.14 2,9.27 8.91,8.26"
                                fill="#000000" stroke="#ffa500" stroke-width="1.5" />
                        </svg>
                    </a>

                    <a href="">
                        <svg width="32px" height="32px" viewBox="0 0 24 24" fill="none"
                            xmlns="http://www.w3.org/2000/svg">
                            <g id="SVGRepo_bgCarrier" stroke-width="0"></g>
                            <g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g>
                            <g id="SVGRepo_iconCarrier">
                                <path
                                    d="M6.29977 5H21L19 12H7.37671M20 16H8L6 3H3M9 20C9 20.5523 8.55228 21 8 21C7.44772 21 7 20.5523 7 20C7 19.4477 7.44772 19 8 19C8.55228 19 9 19.4477 9 20ZM20 20C20 20.5523 19.5523 21 19 21C18.4477 21 18 20.5523 18 20C18 19.4477 18.4477 19 19 19C19.5523 19 20 19.4477 20 20Z"
                                    stroke="#ffa500" stroke-width="2" stroke-linecap="round"
                                    stroke-linejoin="round"></path>
                            </g>
                        </svg>
                    </a>
                </div>
            </div>
        </div>
        `;

