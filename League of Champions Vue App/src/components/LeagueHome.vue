<template>
<div class="leaguehome">
  <h1>{{nameLeague}}</h1>
  <p>{{greet("Ever")}}</p>
  <b-button class="button" v-b-modal="'name-update-modal'">Update League Name</b-button>
  <b-modal id="name-update-modal" title="Update League Name" hide-footer>
    <input type="text" v-model="tempLeagueName">
    <b-button class="button" id="leagueNameConfirmButton" v-on:click="updateLeagueName">Confirm</b-button>
  </b-modal>
  <br><br>
  <h2>Teams</h2>

  <teams v-for="team in allTeams"
  v-bind:key="team.id"
  v-bind:id="team.id"
  v-bind:clubName="team.teamName"></teams>
  

  <b-button class="button" v-b-modal="'add-team-modal'">Add Team</b-button>
  <b-modal id="add-team-modal" title="Add a new Team" hide-footer>
    <label for="TeamName">TeamName</label>
    <b-input name="TeamName" type="text" v-model="addedClub"></b-input>
    <br>
    <b-button class= "button" v-on:click="storeAddTeam">Add Team</b-button>
  </b-modal>

  <br><br>
  
  <p class="ariel"><b>Latest Club to join this league:</b> {{addedClub}}</p>
  <p class="ariel"><b>Total Teams: </b> {{this.numTeams()}}</p>
  
</div>
</template>

<script lang="ts">

import teams from '@/components/Teams.vue';

export default{
  components: { 
    teams
  },
  created(){
    this.$store.dispatch("updateTeams")
  },

  data(){
    return{
      nameLeague: 'LEAGUE OF THE CHAMPIONS',
      addedClub: "",
      idNum: 0,
      tempLeagueName: ""
    }//return
  },//data

  methods:{
    storeAddTeam() {
      this.idNum = this.numTeams()
      if(this.addedClub.trim() != ""){
        this.$store.dispatch("addTeam", this.idNames(/*this.idNum, */this.addedClub))
      }//if
      else{
        alert("You have not typed in a Team name")
      }
    },
    greet: function(str : string) : string{
      return 'Welcome to the greatest league: ' + str
    },//greet
    idNames: function(/*idGiven:any, */name:string):any{
      return {/*id: idGiven, */teamName: name}
    },
    numTeams: function(){
      return this.$store.getters.getListSize
    },
    updateLeagueName: function(){
      if(this.tempLeagueName.trim() != ""){
        this.nameLeague = this.tempLeagueName;
      }//if
       else{
        alert("You have not typed in a name")
      }
      
    }
  },//methods

  computed: {
    allTeams(){
      return this.$store.getters.getAllTeams
    },
  }
  

};//export default
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
ul {
  list-style-type: none;
}

a {
  color: #3167da;
}
.button{
  border: none;
  color:darkblue;
  background-color: gold;
  border-radius: 10%;
  font-weight: lighter;
  font-family: "Times New Roman", Times, serif;
}
.button:hover{
  background-color: red;
}
.ariel{
  font-family: "Times New Roman", Times, serif;
}
h1{
  text-shadow: 1px 1px 2px #e1e6ec, 0 0 1em #2c3e50, 0 0 0.2em #2c3e50;
}
#leagueNameConfirmButton{
  margin-left: 2%;
}

</style>