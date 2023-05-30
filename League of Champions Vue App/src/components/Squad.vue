<template>
  <div class="squad0">
      <!-- <p>{{$route.params.id}}</p> -->
      <div v-if="team">
        <h1>{{team.teamName}}</h1>
        <br>
        <h2>Squad Data</h2>
        <br><br>
        <div>
          <b-table striped hover :items="players"></b-table>
        </div>
        <b-button class="button" v-b-modal="'add-player-modal'">Add Player</b-button>
        <b-modal id="add-player-modal" title="Add a new Player" hide-footer>
          <label for="pName">Player Name</label>
          <b-input type="text" name="pName" v-model="playerName"></b-input>
          <label for="pAge">Age</label>
          <b-input type="text" name="pAge" v-model="playerAge"></b-input>
          <label for="pCountry">Country</label>
          <b-input type="text" name="pCountry" v-model="playerCountry"></b-input>
          <br>
          <b-button class= "button" v-on:click="addToTeam()">Add Player</b-button>
        </b-modal>

        <b-button class="button" v-b-modal="'remove-player-modal'">Remove Player</b-button>
        <b-modal id="remove-player-modal" title="Remove a Player" hide-footer>
          <label for="pName">Player Name</label>
          <b-input type="text" name="pName" v-model="playerToRemove"></b-input>
          <br>
          <b-button class= "button" v-on:click="removePlayer()">Delete Player</b-button>
        </b-modal>
  <br><br>
      </div>
  </div>
</template>

<script>

import axios from "axios"

const config = {
  headers: { 'Content-Type': 'application/json;charset=UTF-8'}
};

export default{
   created(){
    this.updateSquad()
  },

  data() {
    return {
      players: [],
      playerName: "",
      playerAge: null,
      playerCountry: "",
      playerToRemove: "",
    }//return
  },
  methods: {
    //adds a given player to this team
    async addToTeam(){
      const playerTeam = this.team.teamName
       if(this.playerName.trim() != "" && this.playerAge!=null && this.playerCountry.trim()!=""){
          const tempPlayer = {playerName: this.playerName.trim(),age: parseInt(this.playerAge), country: this.playerCountry.trim(), teamName: playerTeam}
          const res = await axios.post("http://localhost:8083/training/player/", tempPlayer, config)
          this.updateSquad()
      }//if
      else{
        alert("Please fill out all the fields")
      }//else
    },

    async removePlayer(){
      let ind = -1
      ind = this.players.findIndex(x => x.playerName === this.playerToRemove)
      if(ind>-1){
        // this.players.splice(ind, 1)
        const res = await axios.delete("http://localhost:8083/training/player/" + this.playerToRemove + "/" + this.team.teamName, config)
        this.updateSquad()
      }//if
      else{
        alert("Please enter an existing players name also check your spellings")
      }//else
    },//removePlayers

    async updateSquad(){
      try{
        const res = await axios.get("http://localhost:8083/training/player/" + this.team.teamName, config)
        this.players = res.data
      }//try
      catch(error){
        console.log(error)
      }//catch
    }//updateSquad
  },


  computed:{
    team(){
      return this.$store.getters.getTeam(parseInt(this.$route.params.id))
    }//team
  }//computed

}//export default

</script>

<style scoped>
/* Css and styling */
  h1{
    text-shadow: 1px 1px 2px #e1e6ec, 0 0 1em #2c3e50, 0 0 0.2em #2c3e50;
  }
  .button:hover{
    background-color: red;
  }
  .button{
    margin: 1%;
  }
</style>