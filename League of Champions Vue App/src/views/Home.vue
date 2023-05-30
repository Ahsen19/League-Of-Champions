<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/league_logo.png">
    <league-home msg="Welcome to the League of the Champions"/>
    
  </div>
</template>

<script lang="ts">

import LeagueHome from '@/components/LeagueHome.vue'; // @ is an alias to /src

export default {
  name: "Home",
  components: {
    LeagueHome
  }
};

</script>
<style>
  img{
    height: 160pt;
    width: 460pt;
  }
</style>