// server.js
const jsonServer = require('json-server');
const server = jsonServer.create();
const router = jsonServer.router('mock/db.json');
const middlewares = jsonServer.defaults();

server.use(middlewares);

// 여기서 커스텀 라우트를 설정할 수 있습니다.
server.use(
  jsonServer.rewriter({
    '/trips/:tripId/dailies': '/dailies?tripId=:tripId',
  })
);

server.use(router);
server.listen(3010, () => {
  console.log('JSON Server is running');
});
