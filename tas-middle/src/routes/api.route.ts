import { NextFunction, Request, Response, Router } from 'express';
import jwt from 'jsonwebtoken';
import axios from 'axios';

class APIRoute {
    
    public router: Router;

    constructor() {
        this.router = Router();

        //Inicia as rotas
        this.init();
    }

    private validateToken(req: Request): void {
        //Verificar se a requisição tem um Authorization
        if (req.headers.authorization && req.headers.authorization.split(' ')[0] == 'Bearer') {
            let token  = req.headers.authorization.split(' ')[1];

            try {

                jwt.verify( token, process.env.SECRET);

            } catch(error) {
                throw ({message: error.name == 'TokenExpiredError' ? 'Token expired' : 'Token invalid'});
            }
        } else {
            throw ({message: 'Request unauthorized'})
        }
    }

    private init(): void {
        this.router.route('/:recurso')
            .all((req: Request, res: Response, next: NextFunction) => {
                
                try {
                    this.validateToken(req);
                    next();
                } catch (error) {
                    res.status(401).json(error);
                }
        
            })
            .get((req: Request, res: Response) => {
                axios.get(process.env.SERVER +'/'+ req.params.recurso)
                    .then(result => {
                        res.status(result.status).json(result.data)
                    })
                    .catch(error => {
                        console.log(error)
                        res.json(error)
                    });
            })
            .post((req: Request, res: Response) => {
                axios.post(process.env.SERVER +'/'+ req.params.recurso, req.body)
                    .then(result => {
                        res.status(result.status).json(result.data)
                    })
                    .catch(error => {
                        console.log(error)
                        res.json(error)
                    });
            });


        this.router.route('/:recurso/:id')
            .all((req: Request, res: Response, next: NextFunction) => {
                
                try {
                    this.validateToken(req);
                    next();
                } catch (error) {
                    res.status(401).json(error);
                }
        
            })
            .get((req: Request, res: Response) => {
                axios.get(process.env.SERVER +'/'+ req.params.recurso +'/'+ req.params.id)
                    .then(result => {
                        res.status(result.status).json(result.data)
                    })
                    .catch(error => {
                        res.status(error.response.status)
                            .json(error.response.data)
                    });
            })
            .put((req: Request, res: Response) => {
                axios.put(process.env.SERVER +'/'+ req.params.recurso +'/'+ req.params.id, req.body)
                    .then(result => {
                        res.status(result.status).json(result.data)
                    })
                    .catch(error => {
                        res.status(error.response.status)
                            .json(error.response.data)
                    });
            })  
            .delete((req: Request, res: Response) => {
                axios.delete(process.env.SERVER +'/'+ req.params.recurso +'/'+ req.params.id)
                    .then(result => {
                        res.status(result.status).json(result.data)
                    })
                    .catch(error => {
                        res.status(error.response.status)
                            .json(error.response.data)
                    });
            });         
    }
}

export default new APIRoute().router;