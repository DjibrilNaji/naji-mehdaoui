"use client"

import { Button } from "@/components/ui/button"
import { Checkbox } from "@/components/ui/checkbox"
import { Input } from "@/components/ui/input"
import {
  Select,
  SelectContent,
  SelectGroup,
  SelectItem,
  SelectLabel,
  SelectTrigger,
  SelectValue
} from "@/components/ui/select"
import routes from "@/routes"
import { create } from "@/services/PartyService"
import { Address } from "@/types/address"
import { Party, PartyForm } from "@/types/party"
import { formatAddress } from "@/utils/formatAddress"
import { useMutation, useQueryClient } from "@tanstack/react-query"
import { useRouter } from "next/navigation"
import { useState } from "react"

interface CreatePartyFormProps {
  addresses: Address[]
}

export default function CreatePartyForm({ addresses }: CreatePartyFormProps) {
  const router = useRouter()
  const [formData, setFormData] = useState<PartyForm>({
    name: "",
    count_people: 0,
    max_people: 1,
    start_timestamp: new Date(),
    end_timestamp: new Date(),
    publication: new Date(),
    price: 0,
    bring_something: false,
    type: { id: "" },
    address: { id: "" },
    photos: [],
    creator: { id: "1a7b6c5d-2e4a-4f3c-8b9f-a2d1e4f7c1a9" }
  })
  const queryClient = useQueryClient()
  const mutation = useMutation({
    mutationFn: (data: PartyForm) => create(data),
    onSuccess: async (value: Party) => {
      await queryClient.invalidateQueries({ queryKey: ["parties"] })
      router.push(routes.party(value.id))
    }
  })

  function onSubmit(e: React.FormEvent) {
    e.preventDefault()
    mutation.mutate(formData)
  }

  function handleInputChange(e: React.ChangeEvent<HTMLInputElement>) {
    const { name, value } = e.target
    setFormData((prev) => ({ ...prev, [name]: value }))
  }

  return (
    <form onSubmit={onSubmit} className="w-2/3 space-y-6">
      <div>
        <label htmlFor="name" className="block">
          Party Name
        </label>
        <Input
          id="name"
          name="name"
          value={formData.name}
          onChange={handleInputChange}
          placeholder="Party name"
        />
      </div>

      <div>
        <label htmlFor="max_people" className="block">
          Max People
        </label>
        <Input
          id="max_people"
          name="max_people"
          type="number"
          min={1}
          value={formData.max_people}
          onChange={handleInputChange}
          placeholder="Max people"
        />
      </div>

      <div>
        <label htmlFor="start_timestamp" className="block">
          Start Date
        </label>
        <Input
          id="start_timestamp"
          name="start_timestamp"
          type="date"
          value={formData.start_timestamp.toISOString().split("T")[0]} // format as yyyy-mm-dd
          onChange={(e) => setFormData({ ...formData, start_timestamp: new Date(e.target.value) })}
        />
      </div>

      <div>
        <label htmlFor="end_timestamp" className="block">
          End Date
        </label>
        <Input
          id="end_timestamp"
          name="end_timestamp"
          type="date"
          value={formData.end_timestamp.toISOString().split("T")[0]} // format as yyyy-mm-dd
          onChange={(e) => setFormData({ ...formData, end_timestamp: new Date(e.target.value) })}
        />
      </div>

      <div>
        <label htmlFor="price" className="block">
          Price
        </label>
        <Input
          id="price"
          name="price"
          type="number"
          min={0}
          value={formData.price}
          onChange={handleInputChange}
          placeholder="Price"
        />
      </div>
      <div className="flex items-center space-x-2">
        <Checkbox
          id="terms"
          checked={formData.bring_something}
          onCheckedChange={(e) => setFormData({ ...formData, bring_something: Boolean(e) })}
        />
        <label
          htmlFor="terms"
          className="text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70"
        >
          Bring Something
        </label>
      </div>

      <Select onValueChange={(e) => setFormData({ ...formData, type: { id: e } })}>
        <SelectTrigger className="w-[200px]">
          <SelectValue placeholder="Select party type" />
        </SelectTrigger>
        <SelectContent>
          <SelectGroup>
            <SelectLabel>Select party type</SelectLabel>
            <SelectItem value="1">Classic</SelectItem>
            <SelectItem value="2">Board game</SelectItem>
            <SelectItem value="3">Video game</SelectItem>
          </SelectGroup>
        </SelectContent>
      </Select>

      <Select
        onValueChange={(e) => setFormData({ ...formData, address: { id: e } })}
        value={formData.address.id}
      >
        <SelectTrigger className="w-fit">
          <SelectValue placeholder="Select address" />
        </SelectTrigger>
        <SelectContent>
          <SelectGroup>
            <SelectLabel>Select address</SelectLabel>
            {addresses.map((address, index) => (
              <SelectItem key={index} value={address.id.toString()}>
                {formatAddress(address)}
              </SelectItem>
            ))}
          </SelectGroup>
        </SelectContent>
      </Select>

      <Button type="submit">Submit</Button>
    </form>
  )
}
