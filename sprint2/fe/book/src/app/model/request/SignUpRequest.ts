export interface SignUpRequest{
  name?:string;
  email?:string;
  password?:string;
  confirmPassword?:string;
  gender?:number;
  address?: string;
  phone?:string;
  dayOfBirth?:string;
  note?: string;
}
