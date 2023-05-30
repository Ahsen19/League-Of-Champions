<template>
  <div class="allTeams">
    <!-- Html and stuff -->
    <br>
    <b>{{clubName}}</b>
    <b-button class="button" id="link-button"> <router-link id="link-router" v-bind:to="'/squad/'+this.id">Team Website</router-link></b-button>
    <b-button class= "button" v-on:click="storeRemoveTeam">Remove</b-button>
    
   
    <br>
  </div>
</template>

<script lang="ts"> 
// Javascript and coding

export default {
  components: { 

   },
  props: {
    clubName: {
      type: String
    },
    id:{
      type: Number
    }
  },
  data() {
    return {
      squadId: (this.$router as any).params.id
    }
  },
  computed: {
    
  },
  watch: {
   
  },
  methods: {
    storeRemoveTeam() {
      this.$store.dispatch("removeTeam", this.id)
    },
    selectedTeam(){
      return this.$store.getters.getTeam(this.id)
    }
  }
}//export default
</script>

<style scoped>
/* Css and styling */
.allTeams {
  background: darkblue;
  color: white;
  margin: 0% 0% 3% 0%;
  padding-bottom: 1%;
}
.button{
  border: none;
  color:darkblue;
  background-color: rgb(201, 196, 196);
  padding: 2px;
  border-radius: 5px;
  font-weight: lighter;
  font-family: "Times New Roman", Times, serif;
  float: right;
}
.button:hover{
  background-color: red;
}
body{
  font-family: "montserrat", sans-serif;
}
#link-button{
  float: left;
}
#link-router:link{
  color: darkblue;
}
#link-router:visited{
  color: darkblue;
}
#link-router:hover{
  color: white;
}
#link-router:active{
  color: lightblue;
}
</style>