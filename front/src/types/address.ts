import { Party } from "./party"

export type Address = {
  id: string
  num_path: number
  name_path: string
  city?: string
  postal_code: string
  parties?: Party[]
}
