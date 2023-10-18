import {Car} from "./car";

export class User {
  id!:string|null;
  cpf!:string;
  firstName!:string;
  lastName!:string;
  cel!:string;
  email!:string;
  cars!:Car[]
}
