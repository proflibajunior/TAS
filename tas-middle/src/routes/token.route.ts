import { Request, Response, Router } from 'express';
import jwt from 'jsonwebtoken';

class TokenRoute {
    
    public router: Router;

    constructor() {
        this.router = Router();

        //Inicia as rotas
        this.init();
    }

    private init(): void {
        this.router.post('/', (req: Request, res: Response) => {
            
            if (req.body.appkey === process.env.APPKEY) {
                const payload = {appkey: process.env.APPKEY};

                res.json({
                    token: jwt.sign(payload, process.env.SECRET, {
                        expiresIn: '24h'
                    })
                });
            } else {
                res.status(401).json({message: 'Invalid Credential'});
            }
                        
        });
    }
}

export default new TokenRoute().router;