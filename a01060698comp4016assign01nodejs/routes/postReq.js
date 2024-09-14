const express = require('express');
const router = express.Router();

router.post(`/:route?`, (req,res)=> {
    const page = req.params.route;
    const dataReq = req.body;

    res.send(`${page[0].toUpperCase() + page.slice(1).toLowerCase()} ${dataReq.name}!`)
});

module.exports = router;