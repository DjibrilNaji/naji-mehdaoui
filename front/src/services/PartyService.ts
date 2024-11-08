import routes from "@/routes"
import { Party } from "@/types/party"
import axios from "axios"

export const findAll = async (): Promise<Party[]> => {
  try {
    const { data }: { data: Party[] } = await axios.get(
      `http://localhost:8080${routes.api.parties.get}`
    )

    return data
  } catch (error) {
    throw error
  }
}

export const findById = async (id: string): Promise<Party> => {
  try {
    const { data }: { data: Party } = await axios.get(
      `http://localhost:8080${routes.api.parties.getById(id)}`
    )

    return data
  } catch (error) {
    throw error
  }
}
