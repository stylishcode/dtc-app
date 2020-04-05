<template>
  
    <div v-bind:style="styleObject" class="container" id="app">

    <div class="vld-parent">
        <loading :active.sync="isLoading" 
        :can-cancel="true" 
        :on-cancel="onCancel"
        :is-full-page="fullPage"></loading>

    <b-alert
      :show="dismissCountDown"
      dismissible
      variant="warning"
      @dismissed="dismissCountDown=0"
      @dismiss-count-down="countDownChanged"
    >
      <!-- <p>This alert will dismiss after {{ dismissCountDown }} seconds...</p> -->
      <p>Por favor preencher os seguintes campos: {{ mensagem }} </p>
      <!-- <b-progress
        variant="warning"
        :max="dismissSecs"
        :value="dismissCountDown"
        height="4px"
      ></b-progress> -->
    </b-alert>

      <form @submit.prevent="save">
        <div class="form-row">
          <div class="form-group col-md-8 col-sm-12">
            <label class="form-label" for="input-chave-google">Chave Google:</label>
            <input class="form-control" v-model="coordenadas.chaveGoogle" type="text" id="input-chave-google" placeholder="Chave Google">
          </div>
          <div class="form-group col-md-4 col-sm-12">
            <label class="form-label" for="select-modal">Modalidade</label>
            <select class="form-select form-control" id="select-modal" v-model="coordenadas.modal">
              <option value="walking">Andando</option>
              <option value="biking">Pedalando</option>
              <option value="driving">Dirigindo</option>
            </select>  
          </div>
        </div>
        <div class="form-row">
          <div class="form-group col-md-6">
            <label class="form-label" for="p-origem-t-area">Pontos de Origem</label>
            <textarea class="form-control" v-model="coordenadas.pontoOrigem" id="p-origem-t-area" placeholder="Pontos de origem" rows="5" escape="true"></textarea>
             (Índice, descrição, latitude, longitude, utmX, utmY, detalhe)
          </div>
          <div class="form-group col-md-6">
            <label class="form-label" for="p-destino-t-area">Pontos de Destino</label> 
            <textarea class="form-control" v-model="coordenadas.pontoDestino" id="p-destino-t-area" placeholder="Pontos de destino" rows="5"></textarea>
            (Índice, descrição, latitude, longitude, utmX, utmY, detalhe)
          </div>
        </div>
        <div class="form-row">
          <button class="btn btn-primary input-group-btn">Gerar Resultado</button>
        </div>
        <hr style="border: 0px;"/>
        <div class="form-row">
          <div class="form-group col-md-12">
          <label class="form-label" for="p-resultado-t-area">Resultado</label> 
          <textarea class="form-control" v-model="coordenadas.resultado" id="p-resultado-t-area" placeholder="Resultado" rows="5" readonly>
          </textarea>
          </div>
        </div>
      </form>
    </div>
  </div>
</template>

<script>

  import DadosAnalise from './services/dtc'
  
  import Loading from 'vue-loading-overlay'

  import 'vue-loading-overlay/dist/vue-loading.css'

  export default {
    name: 'app',
    data() {
   //  	styleObject: {
   //  	color: 'red',
   //  	fontSize: '13px'
  	// }
      return {
          isLoading: false,
          fullPage: true,
          coordenadas: {
            modal:'',
            chaveGoogle: '',
            pontoOrigem: '',
            pontoDestino: '',
            resultado: '',            
          },
          dismissSecs: 5,
          dismissCountDown: 0,
          showDismissibleAlert: false,
          mensagem: ''
      }
    },
    components: {
      Loading
    },
    methods: {

      countDownChanged(dismissCountDown) {
        this.dismissCountDown = dismissCountDown
      },
      showAlert() {
        this.dismissCountDown = this.dismissSecs
      },
      save() {
      	this.isLoading = true;
        DadosAnalise.save(this.coordenadas).then(response => {
          this.coordenadas = response.data
          this.isLoading = false;
        }, responseErro => {
          if (responseErro.response.status == 422) {
            this.mensagem = responseErro.response.data
            this.showAlert()                        
          }
          this.isLoading = false;
        })        
      },
      onCancel() {
        console.log('User cancelled the loader.')
      }

    }
  }

</script>
