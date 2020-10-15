import app from './app';
import dotenv from 'dotenv';

//carrega o enviroment
dotenv.config();

app.listen(process.env.PORT, () => {
    console.log(`> Running on port ${process.env.PORT}...`);
});