import Vue from 'vue'
import Vuex from 'vuex'
import axios from "axios";

const config = {
  headers: { 'Content-Type': 'application/json;charset=UTF-8'}
};

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    allTeams: [
      {id:-1, teamName: ""}
    ],
    serverUrl: "http://localhost:8083/training/"
  },


  mutations: {
    addTeam: function(state, club){
      state.allTeams.push(club)
    },//addTeam


    updateTeams(state, teams) {
      state.allTeams = teams
    },//updateTeams


    removeTeam: function(state, idTeam){
      const ind = state.allTeams.findIndex(x => x.id ===idTeam); //used to find the index of an object given a specific property i.e. team id
      state.allTeams.splice(ind, 1)
    }//removeteam
  },


  actions: {

    //updateTeams--whenever this function runs, it updates the list of teams from the database
    /*Should access the database
    Call the getAllTeams in groovy
    return a list of teams
    */ 
    async updateTeams({commit}){
      try{
        const res = await axios.get("http://localhost:8083/training/team" + "/", config)
        commit('updateTeams', res.data)
      }//try
      catch(error){
        console.log(error)
      }//catch
    },//updateTeams


    async removeTeam({commit, dispatch}, clubID){
      try{
        const res = await axios.delete("http://localhost:8083/training/team" + "/" + clubID, config)
        await dispatch('updateTeams')
        // commit('removeTeam', clubID)
      }//try
      catch(error){
        console.log(error)
      }//catch
    },//removeTeam


    async addTeam({commit, dispatch}, club){
      try{
        const tempTeam = {
          teamName: club.teamName}
        const res = await axios.post("http://localhost:8083/training/team", tempTeam, config)
        await dispatch('updateTeams')
        // commit('addTeam', club)
      }//try
      catch(error){
        console.log(error)
      }//catch
    }//addTeam

  },
  getters:{
    getAllTeams: (state) => {
      return state.allTeams
    },
    getTeam: state => (id : any) =>{
      return state.allTeams.find(getTeam => getTeam.id ===id)
    },
    getListSize: (state)=>{
      console.log("Size of the list of all teams in store = "+ state.allTeams.length)
      return state.allTeams.length
    }//listSize
  },
  modules: {
    
  }
})
