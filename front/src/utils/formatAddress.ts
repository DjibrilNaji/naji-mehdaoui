import { Address } from "@/types/address"

export const formatAddress = (address: Address) => {
  return `${address.num_path} ${address.name_path}, ${address.postal_code} ${address.city}`
}
