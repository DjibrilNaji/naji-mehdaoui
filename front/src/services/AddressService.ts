import routes from "@/routes"
import { Address } from "@/types/address"
import axios from "axios"

export const findAll = async (): Promise<Address[]> => {
  try {
    const { data }: { data: Address[] } = await axios.get(
      `http://localhost:8080${routes.api.addresses.get}`
    )

    return data
  } catch (error) {
    throw error
  }
}
