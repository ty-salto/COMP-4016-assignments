const express = require('express');
const router = express.Router();

router.get(`/:route?`, (req,res)=> {
    const page = req.params.route;
    page?
    (page.toLowerCase() === `foo` ? res.send(`bar`) :
    page.toLowerCase() === `kill` ? (res.send(`Bye bye`), process.exit(0)): res.send(`Hello World`)) 
    : res.send(`Hello World`);
});

module.exports = router;