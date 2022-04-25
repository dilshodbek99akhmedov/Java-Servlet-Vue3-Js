<template>
  <div>
    <form @submit="parent">

      <h2>Add Car</h2>
      <div class="form-group">
        <label for="name">Model</label>
        <input type="text" class="form-control" v-model="car.model" id="model"
               placeholder="Enter model" pattern="[A-Za-z0-9]{1,40}">
      </div>
      <div class="form-group">
        <label for="email">Color</label>
        <select class="form-select" aria-label="Default select example" v-model="car.color" id="color">
          <option value="black" selected>Black</option>
          <option value="blue">Blue</option>
          <option value="grey">Grey</option>
          <option value="gold">Gold</option>
          <option value="green">Green</option>
          <option value="orange">Orange</option>
          <option value="purple">Purple</option>
          <option value="red">Red</option>
          <option value="yellow">Yellow</option>
          <option value="white">White</option>
        </select>
      </div>
      <div class="form-group">
        <label for="email">Price</label>
        <input type="text" class="form-control" v-model="car.price" id="price"
               placeholder="Enter price" pattern="[0-9]{1,15}">
      </div>
      <div class="form-group">
        <label for="speed">Speed</label>
        <input type="text" class="form-control" v-model="car.speed" id="speed"
               placeholder="Enter speed" pattern="[0-9]{1,15}">
      </div>
      <div class="footer-modal">
        <button type="button" @click="createCar" class="btn btn-success">Save</button>
        <button type="submit" @click="noneModal" class="btn btn-danger">Back</button>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  props: {
    noneModal: {
      type: Function,
      default: false
    }
  },
  data() {
    return {
      car: {
        model: '',
        color: '',
        price: '',
        speed: ''
      },
    }
  },
  methods: {
    createCar() {
      this.$emit('addCar', this.car);
      this.car = {
        model: '',
        color: '',
        price: '',
        speed: ''
      }
    }
  }
}
</script>

<style>
.footer-modal {
  display: flex;
  justify-content: space-around;
}
</style>