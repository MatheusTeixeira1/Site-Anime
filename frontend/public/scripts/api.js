const apiUrl = 'http://localhost:8080/blueray';

/**
 * Função para realizar o fetch de dados e tratar a resposta.
 * @param {string} url - URL da API a ser chamada.
 * @returns {Array} - Dados recebidos da API.
 */
async function searchAnimes(url) {
    const response = await fetch(url);
    if (!response.ok) throw new Error(`Erro: ${response.status}`);
    return response.json();
}

/**
 * Função para pesquisar animes por nome.
 * @param {string} animeName - Nome do anime a ser pesquisado.
 * @returns {Array} - Lista de animes encontrados.
 */
async function searchAnimeByName(animeName) {
    try {
        const animes = await searchAnimes(`${apiUrl}/name/${encodeURIComponent(animeName)}`);
        return animes;
    } catch (error) {
        console.error('Erro:', error);
        alert(error.message.includes('404') ? 'Nenhum resultado encontrado.' : 'Erro ao realizar a busca.');
        return [];
    }
}

/**
 * Função para pesquisar animes por ID.
 * @param {string} animeId - ID do anime a ser pesquisado.
 * @returns {Object} - Detalhes do anime encontrado.
 */
async function searchAnimeById(animeId) {
    try {
        const anime = searchAnimes(`${apiUrl}/${encodeURIComponent(animeId)}`);
        return anime;
    } catch (error) {
        console.error('Erro:', error);
        alert(error.message.includes('404') ? 'Anime não encontrado.' : 'Erro ao realizar a busca.');
        return null;
    }
}
