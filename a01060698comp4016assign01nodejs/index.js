const express = require('express')
const app = express();
const port = 8080;

//middleware
app.use(express.json());

const getRequest = require(`./routes/getReq`);
const postRequest = require(`./routes/postReq`);

app.use('/', getRequest);
app.use('/', postRequest);

app.listen(port, () => {
  console.log(`Example app listening on port ${port}`)
})