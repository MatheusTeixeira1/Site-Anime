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