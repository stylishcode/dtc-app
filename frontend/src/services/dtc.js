import { http } from './config'

export default {

    // listAll:() => {
    //     return http.get('distancias')
    // },

    save:(coordenada) => {
        return http.post('coordenadas', coordenada)
    }
}