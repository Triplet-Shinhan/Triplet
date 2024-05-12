// 3개 콤마
const makeDots = (text) => text.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ',');
export default makeDots;
