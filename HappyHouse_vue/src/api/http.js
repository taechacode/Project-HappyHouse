import axios from "axios";
import { SERVER_URL } from "@/config";

// axios 객체 생성
export default axios.create({
  baseURL: SERVER_URL,
  headers: {
    "Content-type": "application/json",
  },
});
