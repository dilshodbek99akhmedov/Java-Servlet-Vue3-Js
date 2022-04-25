<template>
  <nav class="navbar navbar-light justify-content-between" style="background-color: #e3f2fd;padding: 16px">
    <h1 class="navbar-brand mb-0">Cars</h1>
    <button class="btn btn-outline-success my-2 my-sm-0" @click="showModal" type="submit">Create Car</button>
  </nav>
</template>

<script>
export default {
  props: {
    showModal: {
      type: Function,
      default: false
    }
  }
}
</script>

<style>

</style>