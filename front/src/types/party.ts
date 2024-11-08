export type Party = {
  id: string
  name: string
  count_people: number
  max_people: number
  start_timestamp: Date
  end_timestamp: Date
  publication: Date
  price: number
  bring_something: boolean
  type: string // TODO: Enum
  address: string // TODO: Address
  videoGames?: string[] // TODO: VideoGame[]
  boardGames?: string[] // TODO: BoardGame[]
  photos: string[] // TODO: Photo[]
  creator: string // TODO: User
  invitedUsers: string[] // TODO: User[]
}
