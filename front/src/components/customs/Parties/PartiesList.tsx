import PartyCard from "@/components/customs/Parties/PartyCard"

export default function PartiesList() {
  const parties = [
    {
      id: "1",
      name: "Party 1",
      count_people: 5,
      max_people: 10,
      start_timestamp: new Date(),
      end_timestamp: new Date(),
      publication: new Date(),
      price: 19.9,
      bring_something: true,
      type: "Jeu de société",
      address: "Paris",
      boardGames: ["Monopoly", "Risk"],
      photos: ["https://github.com/shadcn.png"],
      creator: "Shad",
      invitedUsers: ["Shad", "Toto"]
    }
  ]

  return (
    <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-2 mx-4">
      {parties.map((party, index) => (
        <PartyCard party={party} key={index} />
      ))}
    </div>
  )
}
