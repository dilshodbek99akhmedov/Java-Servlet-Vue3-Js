<template>
  <div class="col-8 offset-2">
    <component-navbar :showModal="showModal"/>
    <my-modal v-model:show="modalVisible" :noneModal="noneModal">
      <component-form @addCar="createCar"/>
    </my-modal>
    <edit-modal v-model:show="editModalVisible" :noneModal="noneModal">
      <component-form-edit @editCar="editCar"></component-form-edit>
    </edit-modal>
    <div v-if="cars.length > 0">
      <component-list @remove="removeCar" @edit="editShowModal" :cars="cars"/>
      <div class="dgroup">
        <paginate
            :page-count=totalPage :page-range="3" :margin-pages="2"
            :click-handler="clickCallback"
            :prev-text="'Prev'" :next-text="'Next'"
            :container-class="'pagination'"
            :page-class="'page-item'"
        >
        </paginate>
        <div @click="ordChange" class="btn btn-danger">Ord</div>
      </div>
    </div>
    <div v-else>
      <h2 style="color: red">We can not found any cars</h2>
    </div>

  </div>
</template>

<script>
import ComponentNavbar from "@/components/ComponentNavbar";
import ComponentList from "@/components/ComponentList";
import ComponentForm from "@/components/ComponentForm";
import ComponentFormEdit from "@/components/ComponentFormEdit";
import MyModal from "@/components/UI/MyModal";
import EditModal from "@/components/UI/EditModal";
import axios from 'axios';
import Paginate from "vuejs-paginate-next";

export default {
  components: {
    ComponentNavbar,
    ComponentForm,
    ComponentFormEdit,
    ComponentList,
    paginate: Paginate,
    MyModal,
    EditModal
  },

  data() {
    return {
      count: 0,
      page: 1,
      limit: 10,
      totalPage: 0,
      cars: [],
      modalVisible: false,
      editModalVisible: false,
      ord: false,
      editId: ''
    }
  },
  methods: {
    createCar(car) {
      try {
        axios.post('http://localhost:9090/api/car/create?model=' + car.model + '&color='
            + car.color + '&price=' + car.price + '&speed=' + car.speed);
      } catch (e) {
        console.log(e.info)
      }
      this.modalVisible = false;
      this.fetchCars();
    },
    removeCar(car) {
      try {
        axios.post('http://localhost:9090/api/car/delete?id=' + car.id);
      } catch (e) {
        console.log(e.info)
      }
      this.fetchCars();
    },
    showModal() {
      this.modalVisible = true;
    },
    editShowModal(car) {
      this.editModalVisible = true;
      this.editId = car.id
    },
    editCar(car) {
      try {
        axios.post('http://localhost:9090/api/car/update?id=' + this.editId + '&model=' + car.model + '&color='
            + car.color + '&price=' + car.price + '&speed=' + car.speed);
        this.fetchCars();
        this.editModalVisible = false;
        this.editId = '';
      } catch (e) {
        console.log(e.info)
      }
    },
    noneModal() {
      this.modalVisible = false;
      this.editModalVisible = false;
    },
    clickCallback(pageNum) {
      this.page = pageNum;
      this.fetchCars();
    },
    ordChange() {
      this.ord = !this.ord;
      console.log(this.ord);
      this.fetchCars();
    },
    async fetchCars() {
      try {
        const response = await axios.get('http://localhost:9090/api/cars', {
          params: {
            limit: this.limit,
            page: this.page,
            ord: this.ord
          }
        });
        this.totalPage = Math.ceil(response.data.pages / this.limit)
        this.cars = response.data.cars;
      } catch (e) {
        console.log(e.info)
      }
    }
  },
  mounted() {
    this.fetchCars();
  }
}
</script>

<style>
@import "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css";

.modal {
  display: flex;
}

.dgroup {
  display: flex;
  justify-content: space-between;
}

.btn-danger, .btn-dark {
  height: max-content;
}

button, .form-group, .car {
  margin-bottom: 16px;
}
</style>